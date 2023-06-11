package com.test.myapplication.domain.repository

import com.test.myapplication.models.domain.ImageData
import com.test.myapplication.models.domain.ImagesData

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
interface ImagesRepository {
    /**
     * Load images data for [page] or throw error
     */
    suspend fun loadImages(query: String?, page: Int): ImagesData

    /**
     * Load image data for [id] or throw error
     */
    suspend fun loadImage(id: Long): ImageData
}