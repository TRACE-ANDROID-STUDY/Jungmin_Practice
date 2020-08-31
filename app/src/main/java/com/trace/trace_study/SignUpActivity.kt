package com.trace.trace_study

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signup.setOnClickListener {
            if (!editText.text.isNullOrBlank() && !editText3.text.isNullOrBlank() && !editText4.text.isNullOrBlank() && !editText5.text.isNullOrBlank()){
                if (editText4.text.toString() == editText5.text.toString()){
                    var intent= Intent(this, LoginActivity::class.java)
                    intent.putExtra("email", editText.text.toString())
                    intent.putExtra("password", editText4.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    //startActivity(intent)
                    Toast.makeText(this, "회원가입성공", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "비밀번호와 비밀번호 확인란의 값이 다릅니다", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }
}