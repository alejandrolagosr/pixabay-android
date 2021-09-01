package com.flagos.photos.di

import com.flagos.photos.data.PixabayApi
import com.flagos.photos.data.PixabayApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providePixabayApi(retrofit: Retrofit): PixabayApi = retrofit.create(PixabayApi::class.java)
}
