package org.android.go.sopt.data.remote.service

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Multipart as Multipart1

interface ImageService {
    @Multipart1
    @POST("upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<Unit>
}