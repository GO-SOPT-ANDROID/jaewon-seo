package org.android.go.sopt.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.dto.ResponseGetMusicDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicViewModel : ViewModel() {

    val musicListLiveData: MutableLiveData<List<ResponseGetMusicDto.Data.Music>> = MutableLiveData()

    fun fetchMusicData(id: String) {
        val followerService = ServicePool.getMusicService
        followerService.getMusic(id).enqueue(object : Callback<ResponseGetMusicDto> {
            override fun onResponse(
                call: Call<ResponseGetMusicDto>,
                response: Response<ResponseGetMusicDto>
            ) {
                if (response.isSuccessful) {
                    Log.d("h", "HH")
                    musicListLiveData.value = response.body()?.data?.musicList
                } else {
                    // handle failure
                }
            }

            override fun onFailure(call: Call<ResponseGetMusicDto>, t: Throwable) {
                // handle failure

            }
        })
    }
}