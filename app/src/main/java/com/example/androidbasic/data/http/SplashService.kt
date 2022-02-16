package com.example.androidbasic.data.http

import com.example.androidbasic.data.model.Splash
import retrofit2.Response
import retrofit2.http.GET

interface SplashService {
    @GET("/x/v2/splash/brand/list")
    suspend fun getSplash() : Response<Splash>
}