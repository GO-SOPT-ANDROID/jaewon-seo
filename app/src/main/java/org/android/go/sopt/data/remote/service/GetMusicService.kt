package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.dto.ResponseGetMusicDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetMusicService {
    @GET("{id}/music")
    fun getMusic(
        @Path(value = "id") id: String
    ): Call<ResponseGetMusicDto>
}