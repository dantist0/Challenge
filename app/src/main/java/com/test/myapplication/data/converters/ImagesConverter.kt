package com.test.myapplication.data.converters

import com.test.myapplication.models.data.PixabayHitResponse
import com.test.myapplication.models.data.PixabayImagesResponse
import com.test.myapplication.models.domain.ImageData
import com.test.myapplication.models.domain.ImagesData

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class ImagesConverter {
    fun convert(response: PixabayImagesResponse) = ImagesData(
        total = response.total,
        totalHits = response.totalHits,
        images = response.hits.map { convertHitResponse(it) }
    )

    private fun convertHitResponse(response: PixabayHitResponse) = ImageData(
        id = response.id,
        pageUrl = response.pageUrl,
        type = response.type,
        tags = response.tags,
        previewUrl = response.previewUrl,
        previewWidth = response.previewWidth,
        previewHeight = response.previewHeight,
        webFormatUrl = response.webFormatUrl,
        webFormatWidth = response.webFormatWidth,
        webFormatHeight = response.webFormatHeight,
        largeImageUrl = response.largeImageUrl,
        imageWidth = response.imageWidth,
        imageHeight = response.imageHeight,
        imageSize = response.imageSize,
        views = response.views,
        downloads = response.downloads,
        likes = response.likes,
        comments = response.comments,
        userId = response.userId,
        user = response.user,
        userImageUrl = response.userImageUrl
    )
}