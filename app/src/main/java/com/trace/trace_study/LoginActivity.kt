package com.trace.trace_study

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.trace.trace_study.AutoLogin.MySharedPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linear_login)

        if (MySharedPreferences.islogin) {
            Toast.makeText(this, "자동 로그인되었습니다", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MyinstaActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_login.setOnClickListener {
            if(!editText.text.isNullOrBlank() && !editText2.text.isNullOrBlank()) {

               // MySharedPreferences.tmpjwt = myjwt
                MySharedPreferences.email = editText.text.toString()
                MySharedPreferences.password = editText2.text.toString()

                Toast.makeText(this, "로그인되었습니다", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, MyinstaActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "아이디와 비밀번호를 모두 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
        to_signup.setOnClickListener{
            var intent= Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent,1)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                1->{
                    editText.setText(data!!.getStringExtra("email"))
                    editText2.setText(data!!.getStringExtra("password"))
                }
            }

        }

    }
}