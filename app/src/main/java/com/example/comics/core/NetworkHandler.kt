package com.example.comics.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class NetworkHandler {

    fun <T> handleResponseToFlow(call: suspend () -> Response<T>): Flow<T> {
        return flow {
            val result = handleResponseToResult(call).getOrThrow()
            emit(result)
        }
    }

    private suspend fun <T> handleResponseToResult(call: suspend () -> Response<T>): Result<T> {
        return runSafely {
            val response = call.invoke()
            val body = response.body()

            when {
                response.isSuccessful && body != null -> Result.success(body)
                else -> handleException()
            }
        }
    }

    private suspend fun <T> runSafely(call: suspend () -> Result<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                call()
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun <T> handleException(ex: Exception? = null): Result<T> {
        return ex?.let {
            Result.failure(it)
        } ?: run {
            Result.failure(Exception())
        }
    }
}
