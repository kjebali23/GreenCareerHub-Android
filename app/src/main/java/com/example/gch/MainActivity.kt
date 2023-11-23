package com.example.gch

import QuizFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.gch.databinding.ActivityMainBinding
import com.example.gch.fragment.AchievementFragment
import com.example.gch.fragment.FormationFragment
import com.example.gch.fragment.CertifFragment
import com.example.gch.fragment.EntrepriseFormationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnFormation.setOnClickListener {

            changeFragment(FormationFragment(), "")
        }

        binding.btnQuiz.setOnClickListener {

        }

        binding.btnAchievements.setOnClickListener {


            changeFragment(AchievementFragment(), "")
        }

        binding.btnCertif.setOnClickListener {


            changeFragment(EntrepriseFormationFragment(), "")
        }

        binding.btnQuiz.setOnClickListener {


            changeFragment(QuizFragment(), "")
        }

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
