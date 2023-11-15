package com.example.gch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gch.databinding.ActivityMainBinding
import com.example.gch.databinding.ActivityQuizBinding
import com.example.gch.databinding.FragmentQuizBinding
import com.example.gch.databinding.SingleCertifBinding
import com.example.gch.fragment.AchievementFragment
import com.example.gch.fragment.CertifFragment
import com.example.gch.fragment.FormationFragment

class QuizActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuizBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linearRowarrowleft.setOnClickListener{
        val intent = Intent( this , MainActivity::class.java).apply {
//

        }
        this.startActivity(intent)
    }


    }
}