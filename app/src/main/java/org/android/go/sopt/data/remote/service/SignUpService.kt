package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up") // path parameter
    fun login(
        @Body request: RequestSignUpDto, //함수에 파라미터를 넘김
    ): Call<ResponseSignUpDto> // return 값 Call로 받아옴
}