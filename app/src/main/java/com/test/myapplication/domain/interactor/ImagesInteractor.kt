package com.test.myapplication.domain.interactor

import com.test.myapplication.domain.repository.ImagesRepository

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class ImagesInteractor(private val imagesRepository: ImagesRepository) {
    suspend fun loadImages(query: String?, page: Int) = imagesRepository.loadImages(query, page)

    suspend fun loadImage(id: Long) = imagesRepository.loadImage(id)
}