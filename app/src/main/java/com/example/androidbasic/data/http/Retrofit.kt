package com.example.androidbasic.data.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.bilibili.com")
    .addConverterFactory(GsonConverterFactory.create()).build()
val videoService: VideoService = retrofit.create(VideoService::class.java)