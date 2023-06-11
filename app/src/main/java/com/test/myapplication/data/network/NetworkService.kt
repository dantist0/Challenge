package com.test.myapplication.data.network

import com.test.myapplication.models.data.PixabayImagesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "37189632-b06e618b930a69da227c84330"

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
interface NetworkService {

    @GET("?key=$API_KEY&image_type=photo")
    suspend fun getImages(
        @Query("q") query: String?,
        @Query("page") page: Int
    ): PixabayImagesResponse

    @GET("?key=$API_KEY")
    suspend fun getSingleImage(
        @Query("id") id: Long
    ): PixabayImagesResponse
}