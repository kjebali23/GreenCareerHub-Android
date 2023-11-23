package com.example.gch

import QuizFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gch.databinding.ActivityEntrepriseBinding
import com.example.gch.databinding.ActivityMainBinding
import com.example.gch.fragment.AchievementFragment
import com.example.gch.fragment.EntrepriseFormationFragment
import com.example.gch.fragment.FormationFragment

class EntrepriseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntrepriseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityEntrepriseBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnFormation.setOnClickListener {

            changeFragment(EntrepriseFormationFragment(), "")
        }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)

        }
//
//        binding.btnAchievements.setOnClickListener {
//
//
//            changeFragment(AchievementFragment(), "")
//        }

//        binding.btnCertif.setOnClickListener {
//
//
//            changeFragment(EntrepriseFormationFragment(), "")
//        }
//
//        binding.btnQuiz.setOnClickListener {
//
//
//            changeFragment(QuizFragment(), "")
//        }

    }

    private fun changeFragment(fragment: Fragment, name: String) {
        if (name.isEmpty())
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        else
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(name)
                .commit()
    }
}