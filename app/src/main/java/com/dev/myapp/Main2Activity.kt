package com.dev.myapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dev.myapp.databinding.ActivityLayoutBinding
import com.dev.myapp.databinding.ActivityMain2Binding

class Main2Activity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"

        const val EXTRA_RETURN_VALUE = "extra_return_value"
        const val RESULT_CODE = 110
    }

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val student = if(Build.VERSION.SDK_INT>=33){
            intent.getParcelableExtra(EXTRA_DATA, Student::class.java)
        }
        else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if(student!=null){
            binding.text1.text = student.name
        }

        binding.btnSubmit.setOnClickListener {
            val name = binding.edtName.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RETURN_VALUE, name)
            setResult(RESULT_CODE, resultIntent)
            finish()
        }
    }
}