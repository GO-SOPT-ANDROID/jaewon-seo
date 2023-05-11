package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.model.RequestSignInDto
import org.android.go.sopt.data.remote.model.ResponseSignInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface ReqresService {
    @GET("/api/users?page=1")
    fun login(
        @Body request: RequestSignInDto,
    ): Call<ResponseSignInDto>
}