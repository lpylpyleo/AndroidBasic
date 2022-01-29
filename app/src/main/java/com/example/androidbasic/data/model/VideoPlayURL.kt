package com.example.androidbasic.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoPlayURL (
    val code: Long,
    val message: String,
    val ttl: Long,
    val data: Data
)

@Serializable
data class Data (
    val from: String,
    val result: String,
    val message: String,
    val quality: Long,
    val format: String,
    val timelength: Long,

    @SerialName("accept_format")
    val acceptFormat: String,

    @SerialName("accept_description")
    val acceptDescription: List<String>,

    @SerialName("accept_quality")
    val acceptQuality: List<Long>,

    @SerialName("video_codecid")
    val videoCodecid: Long,

    @SerialName("seek_param")
    val seekParam: String,

    @SerialName("seek_type")
    val seekType: String,

    val durl: List<Durl>
)

@Serializable
data class Durl (
    val order: Long,
    val length: Long,
    val size: Long,
    val ahead: String,
    val vhead: String,
    val url: String,

    @SerialName("backup_url")
    val backupURL: List<String>
)
