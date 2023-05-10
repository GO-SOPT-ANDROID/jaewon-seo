package org.android.go.sopt.signUp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.sginIn.SignInActivity

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

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

                    makeSnackbarMessage("회원가입이 완료되었습니다.")
                    finish()
                } else {
                    makeSnackbarMessage("정보를 다시 입력해주시길 바랍니다.")
                }
            }
        }
    }

    private fun makeSnackbarMessage(string: String) {
        Snackbar.make(
            binding.root,string,Snackbar.LENGTH_SHORT
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