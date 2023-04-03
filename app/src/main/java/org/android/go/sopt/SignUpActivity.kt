package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding)
        {
                buttonSignupComplete.setOnClickListener {
                    Log.d("a","dd")
                    if(edittextSignupId.length()>=6 && edittextSignupId.length()<=10 && // 6 <= id  <=10
                        edittextSignupPw.length() >=8 && edittextSignupPw.length() <=12 && // 8 <= pw < =12
                            edittextSignupName.length() !=0 && edittextSignupSpecialty.length() != 0){ // 특기 이름 != 0

                        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
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
}