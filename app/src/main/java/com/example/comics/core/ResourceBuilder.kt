package com.example.comics.core

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceBuilder @Inject constructor(private val context: Context) {

    fun getString(@StringRes id: Int) = context.getString(id)
}