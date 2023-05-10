package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.model.RequestSignInDto
import org.android.go.sopt.data.remote.model.ResponseSignInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("sign-in")
    fun login(
        @Body request: RequestSignInDto,
    ): Call<ResponseSignInDto>
}