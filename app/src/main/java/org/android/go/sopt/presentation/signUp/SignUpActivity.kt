package org.android.go.sopt.presentation.signUp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.presentation.sginIn.SignInActivity

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    // LiveData가 저장되어 있는 ViewModel
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSignUpBtnClickListener()
        editOnTextChangeListener()

        //signUnResult 관찰자 설정 통신 성공했을 때 변화 일어남
        viewModel.signUpResult.observe(this) { signUpResult ->
            changeActivity()
            makeToastMessage(
                signUpResult.message
            )
        }
        viewModel.errorResult.observe(this) {errorResult ->
            makeSnackbarMessage(errorResult.message)
        }
    }

    private fun btnSetEnabled() { //입력된 텍스트 조건을 확인하여 회원 가입버튼 활성화
        with(binding) {
            if (canUserSignIn()) buttonSignupComplete.setEnabled(true)
            else buttonSignupComplete.setEnabled(false)
        }
    }

    private fun editOnTextChangeListener() { //텍스트 변경을 감지하여 btnSetEnabled함수 호출
        with(binding) {
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

    private fun changeActivity() {
        // 회원가입 성공시 화면 전환
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        with(binding) {//바로 id,pw가 입력될 수 있게 값 전달
            intent.putExtra("id", edittextSignupId.text.toString()) // id 값 전달
            intent.putExtra("password", edittextSignupPw.text.toString()) // pw 값 전달
        }
        setResult(RESULT_OK, intent)
    }

    private fun setSignUpBtnClickListener() {
        with(binding)
        {
            buttonSignupComplete.setOnClickListener {
                viewModel.signUp(
                    edittextSignupId.text.toString(),
                    edittextSignupPw.text.toString(),
                    edittextSignupName.text.toString(),
                    edittextSignupSpecialty.text.toString()
                )
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