package com.mansour.task.di

import com.mansour.task.business.repositories.abstraction.ImagesRepository
import com.mansour.task.business.repositories.impl.ImagesRepositoryImpl
import com.mansour.task.business.usecases.abstraction.ImagesUseCase
import com.mansour.task.business.usecases.impl.ImagesUseCaseImpl
import com.mansour.task.framework.datasources.remote.abstraction.ImagesDataSource
import com.mansour.task.framework.datasources.remote.impl.ImagesDataSourceImpl
import com.mansour.task.framework.datasources.remote.services.ImagesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImagesModule {

    @Provides
    @Singleton
    fun provideImagesApi(retrofit: Retrofit): ImagesApi =
        retrofit.create(ImagesApi::class.java)


    @Provides
    @Singleton
    fun provideImagesDataSource(gamesApi: ImagesApi): ImagesDataSource =
        ImagesDataSourceImpl(gamesApi)

    @Provides
    @Singleton
    fun provideImagesRepository(dataSource: ImagesDataSource): ImagesRepository =
        ImagesRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideImageUseCase(repository: ImagesRepository): ImagesUseCase =
        ImagesUseCaseImpl(repository)

}