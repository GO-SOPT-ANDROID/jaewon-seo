package org.android.go.sopt.presentation.sginIn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivitySignInBinding
import org.android.go.sopt.presentation.main.FragmentManageActivity
import org.android.go.sopt.presentation.signUp.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private var id: String = ""
    private var pw: String = ""

    lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    // LiveData가 저장되어 있는 ViewModel
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        setLoginButtonListener()
        setSignUpButtonListener()

        //signInResult 관찰자 설정 통신 성공했을 때 변화 일어남
        viewModel.signInResult.observe(this) { signInResult ->
            startActivity(
                Intent(
                    this@SignInActivity,
                    FragmentManageActivity::class.java
                )
            )
            makeToastMessage(
                signInResult.message
            )
        }
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


    private fun makeToastMessage(string: String) {
        Toast.makeText(
            this, string, Toast.LENGTH_SHORT
        ).show()
    }
    private fun setLoginButtonListener() {
        with(binding){
            buttonMainLogin.setOnClickListener {
                viewModel.signIn(
                    binding.edittextMainId.text.toString(),
                    binding.edittextMainPw.text.toString()
                )
            }
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
