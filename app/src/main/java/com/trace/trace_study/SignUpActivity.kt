package com.trace.trace_study

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth=FirebaseAuth.getInstance()

        btn_signup.setOnClickListener {
            if (!editText.text.isNullOrBlank() && !editText3.text.isNullOrBlank() && !editText4.text.isNullOrBlank() && !editText5.text.isNullOrBlank()){
                if (editText4.text.toString() == editText5.text.toString()){
                    /*
                    if (Patterns.EMAIL_ADDRESS.matcher(editText.text.toString()).matches()){
                        Toast.makeText(this, "유효한 이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
                        //Log.d("유효한 이메일",)
                    }else {

                     */
                    if(editText4.text.length<6) {
                        Toast.makeText(this, "비밀번호는 6자 이상으로 설정해주세요", Toast.LENGTH_SHORT).show()
                    }else{
                        auth.createUserWithEmailAndPassword(editText.text.toString(), editText4.text.toString())
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    var intent = Intent(this, LoginActivity::class.java)
                                    intent.putExtra("email", editText.text.toString())
                                    intent.putExtra("password", editText4.text.toString())
                                    setResult(Activity.RESULT_OK, intent)
                                    //startActivity(intent)
                                    Toast.makeText(this, "회원가입성공", Toast.LENGTH_SHORT).show()
                                    finish()

                                } else {
                                    Toast.makeText(this, "회원가입실패 나중에 다시 시도하세요", Toast.LENGTH_SHORT)
                                        .show()
                                    // Log.d("나중에","${task.result}")
                                    Log.d("나중에", "나중에")

                                    // Log.d("나중에","${task.exception}")
                                    //Log.d("나중에","${task}")
                                }

                                // ...
                                //}


                            }
                    }
                }else{
                    Toast.makeText(this, "비밀번호와 비밀번호 확인란의 값이 다릅니다", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }
}