package com.rajand.core.data.source.remote.network

import com.rajand.core.data.source.remote.response.AyahResponse
import com.rajand.core.data.source.remote.response.SurahResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("surah")
    suspend fun getAllSurah(): SurahResponse

    @GET("surah/{numberSurah}")
    suspend fun getAllAyah(
        @Path("numberSurah") numberSurah: Int
    ): AyahResponse
}