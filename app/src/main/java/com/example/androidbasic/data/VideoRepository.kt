package com.example.androidbasic.data

import com.example.androidbasic.data.http.VideoService
import com.example.androidbasic.data.http.videoService

class VideoRepository(videoService: VideoService) {
    suspend fun getRecommendVideos(bvid: String) = videoService.getRecommendVideos(bvid)
    suspend fun getStreamUrl(bvid: String,cid:Long) = videoService.getStreamUrl(bvid,cid)
}