package com.trace.trace_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.trace.trace_study.AutoLogin.MySharedPreferences

class MyinstaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinsta)
        MySharedPreferences.islogin = true
        Log.d("islogin","${MySharedPreferences.islogin}")


    }
}