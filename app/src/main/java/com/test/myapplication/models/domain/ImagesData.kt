package com.test.myapplication.models.domain

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
data class ImagesData(
    val total: Int,
    val totalHits: Int,
    val images: List<ImageData>
)

data class ImageData(
    val id: Long,
    val pageUrl: String,
    val type: String,
    val tags: String,
    val previewUrl: String,
    val previewWidth: String,
    val previewHeight: String,
    val webFormatUrl: String,
    val webFormatWidth: Int,
    val webFormatHeight: Int,
    val largeImageUrl: String,
    val imageWidth: String,
    val imageHeight: String,
    val imageSize: Int,
    val views: Long,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val userId: Int,
    val user: String,
    val userImageUrl: String
)