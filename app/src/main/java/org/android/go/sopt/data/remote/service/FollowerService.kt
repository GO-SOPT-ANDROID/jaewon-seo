package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.dto.ResponseFollowerDto
import retrofit2.Call
import retrofit2.http.GET

interface FollowerService {
    @GET("/api/users?page=1")
    fun getFollowerList(): Call<ResponseFollowerDto>
}