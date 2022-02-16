package com.example.androidbasic.data.model

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class Splash (
    val code: Long,
    val message: String,
    val ttl: Long,
    val data: Content
)

@Serializable
data class Content (
    @SerialName("pull_interval")
    val pullInterval: Long,

    val forcibly: Boolean,
    val rule: String,
    val list: List<ListElement>,
    val show: List<Show>
)

@Serializable
data class ListElement (
    val id: Long,
    val thumb: String,

    @SerialName("logo_url")
    val logoURL: String
)

@Serializable
data class Show (
    val id: Long,

    @SerialName("begin_time")
    val beginTime: Long,

    @SerialName("end_time")
    val endTime: Long,

    val probability: Long,
    val duration: Long
)
