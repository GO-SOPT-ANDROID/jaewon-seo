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

class SignUpActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignUpViewModel>()
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observe signUpResult LiveData
        viewModel.signUpResult.observe(this) { signUpResult ->
            changeActivity()
            makeToastMessage(signUpResult.message)
        }

        viewModel.errorResult.observe(this) { errorResult ->
            makeSnackbarMessage(errorResult.message)
        }

        setSignUpBtnClickListener()
    }

    private fun setSignUpBtnClickListener() {
        with(binding) {
            buttonSignupComplete.setOnClickListener {
              viewModel?.signUp()
            }
        }
    }

    private fun changeActivity() {
        val intent = Intent().apply {
            putExtra("id", viewModel.id.value) // id 값 전달
            putExtra("password", viewModel.pw.value) // pw 값 전달
        }
        setResult(RESULT_OK, intent)
        finish() // close this activity and go back
    }


    private fun makeSnackbarMessage(string: String) {
        Snackbar.make(binding.root, string, Snackbar.LENGTH_SHORT).show()
    }

    private fun makeToastMessage(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}
