package com.dev.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.myapp.databinding.ActivityLayoutBinding

class LayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}