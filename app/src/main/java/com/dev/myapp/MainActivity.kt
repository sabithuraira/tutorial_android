package com.dev.myapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.dev.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        binding.textBinding.text = "Hello Programmer"
        //INTENT
        //EXPLICIT INTENT
        binding.btn1.setOnClickListener {
            //OPEN MAIN2ACTIVITY
            val intentDestination = Intent(this@MainActivity, Main2Activity::class.java)
//            intentDestination.putExtra(Main2Activity.EXTRA_DATA, "Hello My Friend")
            intentDestination.putExtra(Main2Activity.EXTRA_DATA, Student("Joko", "123456"))
//            startActivity(intentDestination)
            resultLauncher.launch((intentDestination))
        }

        binding.btnPhone.setOnClickListener {
            val number = "123455677"
            val intentPhone = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            startActivity(intentPhone)
        }

        binding.btnRecycler.setOnClickListener {
            val intentDestination = Intent(this@MainActivity, RecyclerActivity::class.java)
            startActivity(intentDestination)
        }

        binding.btnFragment.setOnClickListener {
            val intentDestination = Intent(this@MainActivity, FragmentActivity::class.java)
            startActivity(intentDestination)
        }

        //IMPLICIT INTENT
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> if(result.resultCode==Main2Activity.RESULT_CODE && result.data!=null){
            val name = result.data?.getStringExtra(Main2Activity.EXTRA_RETURN_VALUE)
            Toast.makeText(applicationContext, name, Toast.LENGTH_SHORT).show()
        }
    }
}