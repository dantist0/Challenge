package com.test.myapplication.models.data

import com.google.gson.annotations.SerializedName

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
data class PixabayImagesResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<PixabayHitResponse>
)

data class PixabayHitResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("pageURL") val pageUrl: String,
    @SerializedName("type") val type: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("previewURL") val previewUrl: String,
    @SerializedName("previewWidth") val previewWidth: String,
    @SerializedName("previewHeight") val previewHeight: String,
    @SerializedName("webformatURL") val webFormatUrl: String,
    @SerializedName("webformatWidth") val webFormatWidth: Int,
    @SerializedName("webformatHeight") val webFormatHeight: Int,
    @SerializedName("largeImageURL") val largeImageUrl: String,
    @SerializedName("imageWidth") val imageWidth: String,
    @SerializedName("imageHeight") val imageHeight: String,
    @SerializedName("imageSize") val imageSize: Int,
    @SerializedName("views") val views: Long,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("comments") val comments: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("user") val user: String,
    @SerializedName("userImageURL") val userImageUrl: String
)