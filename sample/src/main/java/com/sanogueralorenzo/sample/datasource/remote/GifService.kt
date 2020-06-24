package com.sanogueralorenzo.sample.datasource.remote

import com.sanogueralorenzo.sample.network.NetworkModule
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {
    @GET("gifs/search")
    suspend fun searchGifs(
        @Query("q") searchTerm: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 30,
        // For security reasons, this should be moved to a C module, also investigate better way instead of query
        @Query("api_key") apiKey: String = NetworkModule.GIPHY_API_KEY
    ): GifsResponse

    @GET("gifs/trending")
    suspend fun getTrendingGifs(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 30,
        // For security reasons, this should be moved to a C module, also investigate better way instead of query
        @Query("api_key") apiKey: String = NetworkModule.GIPHY_API_KEY
    ): GifsResponse
}
