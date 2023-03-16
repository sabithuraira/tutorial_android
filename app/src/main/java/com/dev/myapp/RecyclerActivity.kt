package com.dev.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.myapp.databinding.ActivityLayoutBinding
import com.dev.myapp.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    private val listStudent = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcData.setHasFixedSize(true)
        binding.rcData.layoutManager = LinearLayoutManager(this)

        listStudent.add(Student("Andy", "1234561"))
        listStudent.add(Student("Robert", "1234562"))
        listStudent.add(Student("Joko", "1234563"))
        listStudent.add(Student("Zhack", "1234564"))
        listStudent.add(Student("Saber", "1234565"))
        listStudent.add(Student("Messi", "1234566"))

        val studentAdapter = StudentAdapter(listStudent)
        studentAdapter.setOnClickCallBack(object: StudentAdapter.onClickCallBack{
            override fun onItemClicked(data: Student) {
                showAlert(data)
            }
        })

        binding.rcData.adapter = studentAdapter
    }

    private fun showAlert(data: Student){
        Toast.makeText(this, "You clicke " + data.name, Toast.LENGTH_SHORT).show()
    }
}