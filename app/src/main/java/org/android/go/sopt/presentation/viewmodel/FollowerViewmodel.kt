package org.android.go.sopt.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.dto.ResponseFollowerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {

    val followerListLiveData: MutableLiveData<List<ResponseFollowerDto.Data>> = MutableLiveData()

    fun fetchFollowerData() {
        val followerService = ServicePool.followerService
        followerService.getFollowerList().enqueue(object : Callback<ResponseFollowerDto> {
            override fun onResponse(
                call: Call<ResponseFollowerDto>,
                response: Response<ResponseFollowerDto>
            ) {
                if (response.isSuccessful) {
                    followerListLiveData.value = response.body()?.datas
                } else {
                    // handle failure
                }
            }

            override fun onFailure(call: Call<ResponseFollowerDto>, t: Throwable) {
                // handle failure
            }
        })
    }
}
