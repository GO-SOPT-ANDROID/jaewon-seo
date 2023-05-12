package org.android.go.sopt.presentation.signUp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.dto.RequestSignUpDto
import org.android.go.sopt.data.remote.dto.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.sginIn.SignInActivity
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    private val signUpService = ServicePool.signUpService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSignUpBtnClickListener()
        editOnTextChangeListener()
     }

    private fun btnSetEnabled(){ //입력된 텍스트 조건을 확인하여 회원 가압버튼 활성화
        with(binding){
            if (canUserSignIn()) buttonSignupComplete.setEnabled(true)
            else buttonSignupComplete.setEnabled(false)

        }
    }
    private fun editOnTextChangeListener(){ //텍스트 변경을 감지하여 btnSetEnabled함수 호출
        with(binding){
            edittextSignupId.addTextChangedListener(
                CommonTextWatcher(
                onChanged = { source, _, _, _ -> btnSetEnabled() }
                )
            )
            edittextSignupPw.addTextChangedListener(
                CommonTextWatcher(
                onChanged = { source, _, _, _ -> btnSetEnabled() }
            )
            )
            edittextSignupName.addTextChangedListener(
                CommonTextWatcher(
                onChanged = { source, _, _, _ -> btnSetEnabled() }
            )
            )
            edittextSignupSpecialty.addTextChangedListener(
                CommonTextWatcher(
                onChanged = { source, _, _, _ -> btnSetEnabled() }
            )
            )
        }
    }
    private fun completeSignUp() {
        signUpService.signIUp(
            with(binding) {
                RequestSignUpDto(
                    edittextSignupId.text.toString(),
                    edittextSignupPw.text.toString(),
                    edittextSignupName.text.toString(),
                    edittextSignupSpecialty.text.toString()
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let { makeToastMessage(it) } ?: "회원가입에 성공했습니다."
                    if (!isFinishing) finish()
                } else {
                    // 실패한 응답
                    response.body()?.message?.let { makeToastMessage(it) } ?: "서버통신 실패(40X)"
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                t.message?.let { makeToastMessage(it) } ?: "서버통신 실패(응답값 X)"
            }
        })
    }

    private fun setSignUpBtnClickListener() {
        with(binding)
        {
            buttonSignupComplete.setOnClickListener {
                if (canUserSignIn()) { //id,pw,이름,특기을 올바르게 입력했는 여부 판단
                    completeSignUp() //서버와 통신 시작
                    // 회원가입 성공시 화면 전환
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    with(binding) {//바로 id,pw가 입력될 수 있게 값 전달
                        intent.putExtra("id", edittextSignupId.text.toString()) // id 값 전달
                        intent.putExtra("password", edittextSignupPw.text.toString()) // pw 값 전달
                    }
                    setResult(RESULT_OK, intent)
                } else {
                    makeSnackbarMessage("정보를 다시 입력해주시길 바랍니다.")
                }
            }
        }
    }

    private fun makeSnackbarMessage(string: String) {
        Snackbar.make(
            binding.root, string, Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun makeToastMessage(string: String) {
        Toast.makeText(
            this, string, Toast.LENGTH_SHORT
        ).show()
    }

    private fun canUserSignIn(): Boolean {
        return binding.edittextSignupId.text.length in 6..10
                && binding.edittextSignupPw.text.length in 8..12
                && binding.edittextSignupName.text.isNotBlank()
                && binding.edittextSignupSpecialty.text.isNotBlank()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}