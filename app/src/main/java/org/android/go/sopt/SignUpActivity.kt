package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignUpBinding
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
    }

    private fun setSignUpBtnClickListener() {
        with(binding)
        {
            buttonSignupComplete.setOnClickListener {
                if (canUserSignIn()
                ) { // 특기 이름 != 0
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    intent.putExtra("id", edittextSignupId.text.toString()) // id 값 전달
                    intent.putExtra("password", edittextSignupPw.text.toString()) // pw 값 전달
                    intent.putExtra("name", edittextSignupName.text.toString()) // name 값 전달
                    intent.putExtra("specialty", edittextSignupSpecialty.text.toString()) // pw 값 전달
                    setResult(RESULT_OK, intent)

                    Snackbar.make(
                        binding.root,
                        "회원가입이 완료되었습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    completeSignUp()
                    finish()
                } else {
                    Snackbar.make(
                        binding.root,
                        "정보를 다시 입력해주시길 바랍니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun completeSignUp() {
        signUpService.login(
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
                if (response.isSuccessful) { //서버통신 성공
                    response.body()?.message?.let { makeToastMessage(it) } ?: "회원가입에 성공했습니다."

                    if (!isFinishing) finish()
                } else {
                    // 실패한 응답
                    response.body()?.message?.let { makeToastMessage(it) } ?: "서버통신 실패(40X)"
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                // 왜 안 오노
                t.message?.let { makeToastMessage(it) } ?: "서버통신 실패(응답값 X)"
            }
        })
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