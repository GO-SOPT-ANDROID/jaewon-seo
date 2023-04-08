package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivitySelfIntroduceBinding
import org.android.go.sopt.databinding.ActivitySignUpBinding

class SelfIntroduceActivity : AppCompatActivity() {
    lateinit var binding : ActivitySelfIntroduceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelfIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //resource에 string.xml에서 값 불러옴 + putExtra로 넘겨준 값 받아옴
        binding.textviewSelfintroduceName.text = resources.getString(R.string.name)+ " : " + getIntent().getStringExtra("name")
        binding.textviewSelfintroduceSpecialty.text = resources.getString(R.string.specialty)+ " : " + getIntent().getStringExtra("specialty")
    }
}