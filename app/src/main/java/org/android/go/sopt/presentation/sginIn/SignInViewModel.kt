package org.android.go.sopt.presentation.sginIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool.signInService
import org.android.go.sopt.data.remote.dto.RequestSignInDto
import org.android.go.sopt.data.remote.dto.ResponseSignInDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val _signInResult: MutableLiveData<ResponseSignInDto> = MutableLiveData()
    val signInResult: LiveData<ResponseSignInDto> = _signInResult

    fun signIn(id: String, password: String) {
        signInService.login(
            RequestSignInDto(
                id,
                password
            )
        ).enqueue(object : Callback<ResponseSignInDto> {
            override fun onResponse(
                call: Call<ResponseSignInDto>,
                response: Response<ResponseSignInDto>,
            ) {
                if (response.isSuccessful) {
                    _signInResult.value = response.body()
                } else {
                    // TODO: 에러 처리
                }
            }

            override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                // TODO: 에러 처리
            }
        })
    }
}