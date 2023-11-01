package com.example.comics.model

import com.example.comics.R
import com.example.comics.core.ResourceBuilder
import com.example.comics.data.ItemModel
import com.example.comics.data.ItemVO
import javax.inject.Inject

class ComicsMapper @Inject constructor(
    private val resourceBuilder: ResourceBuilder
) {

    fun toItemVO(itemModel: ItemModel): List<ItemVO> {
        return itemModel.data.results.map {
            ItemVO(
                image = "${it.thumbnail.path}.${it.thumbnail.extension}",
                title = it.title,
                subtitle = it.description ?: resourceBuilder.getString(R.string.without_description)
            )
        }
    }
}