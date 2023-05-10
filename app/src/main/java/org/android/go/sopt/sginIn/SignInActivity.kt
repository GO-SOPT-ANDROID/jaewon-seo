package org.android.go.sopt.sginIn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.RequestSignInDto
import org.android.go.sopt.data.remote.model.ResponseSignInDto
import org.android.go.sopt.databinding.ActivitySignInBinding
import org.android.go.sopt.home.FragmentManageActivity
import org.android.go.sopt.signUp.SignUpActivity
import retrofit2.Call
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private var id: String = ""
    private var pw: String = ""

    lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val signInService = ServicePool.signInService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        setLoginButtonListener()
        setSignUpButtonListener()
    }


    private fun setResultSignUp() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    id = result.data?.getStringExtra("id") ?: ""
                    pw = result.data?.getStringExtra("password") ?: ""

                    with(binding){
                        edittextMainId.setText(id)
                        edittextMainPw.setText(pw)
                    }
                }
            }
    }

    private fun changeActivity(){
        val intent = Intent(this, FragmentManageActivity::class.java)
        startActivity(intent)
    }
    private fun tryLogin() {
        signInService.login(
            with(binding) {
                RequestSignInDto(
                    edittextMainId.text.toString(),
                    edittextMainPw.text.toString()
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseSignInDto> {
            override fun onResponse(
                call: Call<ResponseSignInDto>,
                response: Response<ResponseSignInDto>,
            ) {
                if (response.isSuccessful && response.body()?.status==200) {//서버 통신 성공시,status=200
                        response.body()?.message?.let { makeToastMessage(it) } ?: "아이디 비밀번호 일치"
                        changeActivity()
                    if (!isFinishing) finish()
                } else {
                    // 실패한 응답
                    response.body()?.message?.let { makeToastMessage(it) } ?: "서버통신 실패(40X)"
                }
            }

            override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                t.message?.let { makeToastMessage(it) } ?: "서버통신 실패(응답값 X)"
            }
        })
    }

    private fun makeToastMessage(string: String) {
        Toast.makeText(
            this, string, Toast.LENGTH_SHORT
        ).show()
    }
    private fun setLoginButtonListener() {
        binding.buttonMainLogin.setOnClickListener {
            tryLogin()
        }
    }

    private fun setSignUpButtonListener() {
        binding.buttonMainSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}
