package com.flagos.photos.data

import com.flagos.photos.domain.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/?key=$KEY")
    suspend fun getPhotos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PixabayResponse

    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val KEY = "23120415-e9fe69520752409d88e924224"
    }
}
