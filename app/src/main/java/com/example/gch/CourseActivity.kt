package com.example.gch

import android.content.Intent
import android.net.Uri
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
//                putExtra("test", "test")
            }
            this.startActivity(intent)
        }

        binding.button.setOnClickListener{
            openUrl("https://www.udemy.com/course/maitriser-nodejs-et-son-ecosysteme-npm-express-mongo/")
        }



        val imageRes = intent.getIntExtra("image", 0)
        val title = intent.getStringExtra("title") ?: "" // Use an empty string if title is null
        val description = intent.getStringExtra("description")
        val formatioUrl = intent.getStringExtra("formationUrl")

        val imageView = findViewById<ImageView>(R.id.courseimage)
        imageView.setImageResource(R.drawable.noimage)

        val textView = findViewById<TextView>(R.id.coursetitle)
        textView.text = title

        val descriptionTextView = findViewById<TextView>(R.id.txtDescription)
        descriptionTextView.text = description



    }
    private fun openUrl(link:String){
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW , uri)

        startActivity(intent)

    }
}