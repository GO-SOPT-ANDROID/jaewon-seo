package org.android.go.sopt.presentation.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val specialty = MutableLiveData<String>()

    val idErrorMessage = MutableLiveData<String>()

    val isIdValid = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = validateId(it) }
        addSource(idErrorMessage) { errorMessage ->
            value = errorMessage.isNullOrEmpty()
        }
    }

    val isPwValid = MediatorLiveData<Boolean>().apply {
        addSource(pw) { value = validatePw(it) }
    }

    val isFormValid: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(isIdValid) { value = checkFormValid() }
        addSource(isPwValid) { value = checkFormValid() }
        addSource(name) { value = checkFormValid() }
        addSource(specialty) { value = checkFormValid() }
    }
    companion object {
        private val ID_REGEX = Regex("[a-zA-Z0-9]{6,10}")
        private val PW_REGEX = Regex("[a-zA-Z0-9!@#\$%^&*()]{8,12}")
    }

    fun validateId(id: String?): Boolean {
        return id.isNullOrEmpty() || id.matches(ID_REGEX)
    }

    fun validatePw(pw: String?): Boolean {
        return pw.isNullOrEmpty() || pw.matches(PW_REGEX)
    }

    private fun checkFormValid(): Boolean {
        val isIdValid = validateId(id.value)
        val isPwValid = validatePw(pw.value)
        val isNameValid = !name.value.isNullOrBlank()
        val isSpecialtyValid = !specialty.value.isNullOrBlank()

        return isIdValid && isPwValid && isNameValid && isSpecialtyValid
    }

    fun signUp() {
        signUpService.signIUp(
            RequestSignUpDto(
                id.value ?: "",
                pw.value ?: "",
                name.value ?: "",
                specialty.value ?: ""
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