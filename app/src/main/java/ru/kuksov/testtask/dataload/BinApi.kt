package ru.kuksov.testtask.dataload

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {

    @GET("{id}")
    suspend fun getBin(@Path ("id") id : String) : Response<BinResponse>
}