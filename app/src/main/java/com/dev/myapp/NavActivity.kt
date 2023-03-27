package com.dev.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.myapp.databinding.ActivityMain2Binding
import com.dev.myapp.databinding.ActivityNavBinding
import com.dev.myapp.databinding.FragmentBlank1Binding

class NavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setLoading(data: Boolean){
        if(data){
            binding.progresBar.visibility = View.VISIBLE
        }
        else{
            binding.progresBar.visibility = View.GONE
        }
    }
}