package org.android.go.sopt.data.remote.service


import org.android.go.sopt.data.remote.dto.ResponseFollowerDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("/api/users")
    suspend fun getFollowerList(
        @Query("page") num: Int = 2 //anotation 알아보기
    ): ResponseFollowerDto
}