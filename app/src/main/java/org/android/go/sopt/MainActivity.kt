package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var id:String =""
    private var pw:String =""
    private var name:String =""
    private var specialty:String =""

    lateinit var binding : ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        binding.buttonMainLogin.setOnClickListener {
                if(binding.edittextMainId.text.toString() == id && binding.edittextMainPw.text.toString()==pw) {
                    Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, SelfIntroduceActivity::class.java)
                    intent.putExtra("specialty",specialty)
                    intent.putExtra("name",name)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
        }
        binding.buttonMainSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }


    }
    private fun setResultSignUp(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("password")?:""
                name = result.data?.getStringExtra("name")?:""
                specialty = result.data?.getStringExtra("specialty")?:""
            }
        }
    }

}