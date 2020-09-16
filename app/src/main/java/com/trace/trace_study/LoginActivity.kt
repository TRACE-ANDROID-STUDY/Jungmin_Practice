package com.trace.trace_study

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.trace.trace_study.AutoLogin.MySharedPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var autologincheck : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        if (MySharedPreferences.islogin) {
            Toast.makeText(this, "자동 로그인되었습니다", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MyinstaActivity::class.java)
            startActivity(intent)
            finish()
        }
        login_btn_autologin_picktouchguide.setOnClickListener {
            if (autologincheck == false) {//false
                login_btn_autologin_picktouchguide.setBackgroundResource(R.drawable.login_btn_autologinagree_pick)
                autologincheck = true
                //MySharedPreferences.islogin = true
            } else {//true
                login_btn_autologin_picktouchguide.setBackgroundResource(R.drawable.login_b_btn_autologin_disagree_pick)
                autologincheck = false
                MySharedPreferences.islogin = false
            }
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
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    fun updateUI(currentUser:FirebaseUser?){

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