package com.example.androidbasic.data.model

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json  = Json(JsonConfiguration.Stable)
// val video = json.parse(Video.serializer(), jsonString)


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class VideoList(
    val code: Long,
    val data: List<Video>,
    val message: String
)

@Serializable
data class Video (
    val aid: Long,
    val videos: Long,
    val tid: Long,
    val tname: String,
    val copyright: Long,
    val pic: String,
    val title: String,
    val pubdate: Long,
    val ctime: Long,
    val desc: String,
    val state: Long,
    val duration: Long,

    @SerialName("mission_id")
    val missionID: Long? = null,

    val rights: Map<String, Long>,
    val owner: Owner,
    val stat: Map<String, Long>,
    val dynamic: String,
    val cid: Long,
    val dimension: Dimension,

    @SerialName("short_link")
    val shortLink: String,

    @SerialName("short_link_v2")
    val shortLinkV2: String,

    val bvid: String,

    @SerialName("season_type")
    val seasonType: Long,

    @SerialName("is_ogv")
    val isOgv: Boolean,

    @SerialName("ogv_info")
    val ogvInfo: JsonObject? = null,

    @SerialName("rcmd_reason")
    val rcmdReason: String,

    @SerialName("first_frame")
    val firstFrame: String? = null,

    @SerialName("season_id")
    val seasonID: Long? = null,

    @SerialName("up_from_v2")
    val upFromV2: Long? = null,

    @SerialName("order_id")
    val orderID: Long? = null
)

@Serializable
data class Dimension(
    val width: Long,
    val height: Long,
    val rotate: Long
)

@Serializable
data class Owner(
    val mid: Long,
    val name: String,
    val face: String
)
