package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.dto.RequestSignUpDto
import org.android.go.sopt.data.remote.dto.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up")
    fun signIUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}