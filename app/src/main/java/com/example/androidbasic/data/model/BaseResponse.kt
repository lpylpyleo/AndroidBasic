package com.example.androidbasic.data.model

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import org.paradisehell.convex.transformer.ConvexTransformer
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

@Serializable
data class BaseResponse<T>(
    val code: Int = 0,
    val message: String?,
    val data: T?,
)

