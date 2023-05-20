package org.android.go.sopt.presentation.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool.signUpService
import org.android.go.sopt.data.remote.dto.RequestSignUpDto
import org.android.go.sopt.data.remote.dto.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult

    private val _errorResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val errorResult: LiveData<ResponseSignUpDto> = _errorResult

    fun signUp(id : String,pw : String,name : String, specialty :String) {
        signUpService.signIUp(
            RequestSignUpDto(
                id,
                pw,
                name,
                specialty
            )
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                } else {
                    _errorResult.value = response.body()
                }
            }
            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
            }
        })
    }
}