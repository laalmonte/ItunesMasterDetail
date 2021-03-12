package com.viyahe.itunesmasterdetail.api

import com.viyahe.itunesmasterdetail.datamodel.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackService {

    @GET("search?term=star&apm;country=au&amp;media=movie&amp;all")
    suspend fun getTrackFromApi(): Response<Result>

}