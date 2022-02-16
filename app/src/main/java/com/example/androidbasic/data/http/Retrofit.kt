package com.example.androidbasic.data.http

import com.example.androidbasic.data.model.BaseResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.paradisehell.convex.converter.ConvexConverterFactory
import org.paradisehell.convex.transformer.ConvexTransformer
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.lang.Exception
import java.lang.reflect.Type

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.bilibili.com")
//        .addConverterFactory(ConvexConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


val videoService: VideoService by lazy { retrofit.create(VideoService::class.java) }

val splashService: SplashService by lazy { retrofit.create(SplashService::class.java) }


class MyConvexTransformer : ConvexTransformer {
    private val gson = Gson()

    override fun transform(original: InputStream): InputStream {
        // 先反序列化为 BaseResponse
        val response = gson.fromJson<BaseResponse<JsonElement>>(
            original.reader(),
            object : TypeToken<BaseResponse<JsonElement>>() {
            }.type
        )
        // 判断 Response 是否成功
        // 成功则将 data 数据转换成 InputStream, 最后由具体 Converter 处理
        if (response.code == 0 && response.data != null) {
            return response.data.toString().byteInputStream()
        }
        throw BusinessException(
            "code : " + response.code + " ; message : " + response.message
        )
    }
}

class BusinessException(override val message: String?) : Exception()

class ConvF : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        println("------$type------")
        return null
    }
}

class ConvT<T> : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        TODO("Not yet implemented")
    }
}