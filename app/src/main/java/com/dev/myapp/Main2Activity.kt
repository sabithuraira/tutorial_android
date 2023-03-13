package com.dev.myapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Main2Activity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"

        const val EXTRA_RETURN_VALUE = "extra_return_value"
        const val RESULT_CODE = 110
    }

    private lateinit var text1: TextView
    private lateinit var edtName: EditText
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        text1 = findViewById(R.id.text1)
        edtName = findViewById(R.id.edt_name)
        btnSubmit = findViewById(R.id.btn_submit)
//        val strData = intent.getStringExtra(EXTRA_DATA)

        val student = if(Build.VERSION.SDK_INT>=33){
            intent.getParcelableExtra(EXTRA_DATA, Student::class.java)
        }
        else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if(student!=null){
            text1.text = student.name
        }

        btnSubmit.setOnClickListener {
            val name = edtName.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RETURN_VALUE, name)
            setResult(RESULT_CODE, resultIntent)
            finish()
        }
    }
}