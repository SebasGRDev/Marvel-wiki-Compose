package com.sebasgrdev.marvelwiki.model.data.comicdata

import com.sebasgrdev.marvelwiki.model.domain.comic.Comic

data class Result(
    val characters: Characters,
    val collectedIssues: List<Any?>,
    val collections: List<Any?>,
    val creators: Creators,
    val dates: List<Date>,
    val description: String,
    val diamondCode: String,
    val digitalId: Int,
    val ean: String,
    val events: Events,
    val format: String,
    val id: Int,
    val images: List<Image>,
    val isbn: String,
    val issn: String,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val prices: List<Price>,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val textObjects: List<TextObject>,
    val thumbnail: Thumbnail,
    val title: String,
    val upc: String,
    val urls: List<Url>,
    val variantDescription: String,
    val variants: List<Any?>
) {

    fun toComic(): Comic {
        return Comic(
            id = id,
            title = title,
            resourceURI = resourceURI,
            thumbnail = thumbnail.path,
            date = dates,
            thumbnailExt = thumbnail.extension
        )
    }
}