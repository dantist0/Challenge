package com.test.myapplication.data.repository

import com.test.myapplication.data.converters.ImagesConverter
import com.test.myapplication.data.network.NetworkService
import com.test.myapplication.domain.repository.ImagesRepository
import com.test.myapplication.models.domain.ImageData

/**
 * @author Alexey Kuznetsov 10.06.2023.
 */
class ImagesRepositoryImpl(
    private val networkService: NetworkService,
    private val imagesConverter: ImagesConverter
) : ImagesRepository {
    override suspend fun loadImages(query: String?, page: Int) =
        imagesConverter.convert(networkService.getImages(query, page))

    override suspend fun loadImage(id: Long): ImageData =
        imagesConverter.convert(networkService.getSingleImage(id)).images.first()

}