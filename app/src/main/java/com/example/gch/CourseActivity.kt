package com.example.gch

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.gch.databinding.ActivityCourseBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CourseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCourseBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageArrowleft.setOnClickListener{
            val intent = Intent( this , MainActivity::class.java).apply {
                putExtra("test", "test")
            }
            this.startActivity(intent)
        }

        val imageRes = intent.getIntExtra("image", 0)
        val title = intent.getStringExtra("title") ?: "" // Use an empty string if title is null

        val imageView = findViewById<ImageView>(R.id.courseimage)
        imageView.setImageResource(imageRes)

        val textView = findViewById<TextView>(R.id.coursetitle)
        textView.text = title





    }
}