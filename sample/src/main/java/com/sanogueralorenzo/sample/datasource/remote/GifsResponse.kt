package com.sanogueralorenzo.sample.datasource.remote

import com.sanogueralorenzo.sample.domain.Gif
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifsResponse(
    val data: List<DataResponse>
)

@JsonClass(generateAdapter = true)
data class DataResponse(
    val id: String,
    val url: String,
    val title: String,
    val username: String,
    val images: ImagesResponse
)

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @Json(name = "fixed_height_downsampled") val fixedHeightDownsampled: ImageResponse,
    val original: ImageResponse
)

@JsonClass(generateAdapter = true)
data class ImageResponse(val url: String)

fun GifsResponse.toDomainModel(): List<Gif> = data.map {
    Gif(
        it.id,
        it.url,
        it.title,
        it.username,
        it.images.fixedHeightDownsampled.url,
        it.images.original.url
    )
}
