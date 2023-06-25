package org.android.go.sopt.presentation.sginIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool.signInService
import org.android.go.sopt.data.remote.dto.RequestSignInDto
import org.android.go.sopt.data.remote.dto.ResponseSignInDto
import org.android.go.sopt.util.enqueueUtil


class SignInViewModel : ViewModel() {

    private val _signInResult: MutableLiveData<ResponseSignInDto> = MutableLiveData()
    val signInResult: LiveData<ResponseSignInDto> = _signInResult
    fun signIn(id: String, pw: String) {
        signInService.login(
            RequestSignInDto(
                id,
                pw
            )
        ).enqueueUtil(
            onSuccess = {
                _signInResult.value = it
            },
            onError = {
            }
        )
    }
}