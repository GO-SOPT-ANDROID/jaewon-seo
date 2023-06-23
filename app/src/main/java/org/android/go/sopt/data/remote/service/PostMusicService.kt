package org.android.go.sopt.data.remote.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.android.go.sopt.data.remote.dto.ResponsePostMusicDto
import retrofit2.Call
import retrofit2.http.*

interface PostMusicService {
    @Multipart
    @POST("music")
    fun postMusic(
        @Header("id") id: String,
        @Part image: MultipartBody.Part,
        @Part("title") title: RequestBody,
        @Part("singer") singer: RequestBody
    ): Call<ResponsePostMusicDto>
}