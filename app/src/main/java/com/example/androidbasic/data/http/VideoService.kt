package com.example.androidbasic.data.http

import com.example.androidbasic.data.model.VideoList
import com.example.androidbasic.data.model.VideoPlayURL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface VideoService {
    @GET("/x/web-interface/archive/related")
    suspend fun getRecommendVideos(@Query("bvid") bvid: String): Response<VideoList>

    @GET("/x/player/playurl?qn=32&fnval=0&fnver=0&fourk=0")
    suspend fun getStreamUrl(
        @Query("bvid") bvid: String,
        @Query("cid") cid: Long
    ): Response<VideoPlayURL>
}