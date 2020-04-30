package com.sanogueralorenzo.sample.datasource.remote

import com.sanogueralorenzo.sample.domain.Gif

data class GifsResponse(
    val data: List<DataResponse>
)

data class DataResponse(
    val id: String,
    val url: String,
    val title: String,
    val username: String,
    val images: ImagesResponse
)

@Suppress("ConstructorParameterNaming")
data class ImagesResponse(
    val fixed_height_downsampled: ImageResponse,
    val original: ImageResponse
)

data class ImageResponse(val url: String)

fun GifsResponse.toDomainModel(): List<Gif> = data.map {
        Gif(
            it.id,
            it.url,
            it.title,
            it.username,
            it.images.fixed_height_downsampled.url,
            it.images.original.url
        )
    }
