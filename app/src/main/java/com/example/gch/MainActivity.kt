package com.example.gch

import HttpGetRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gch.databinding.ActivityMainBinding
import com.example.gch.fragment.AchievementFragment
import com.example.gch.fragment.FormationFragment
import com.example.gch.fragment.QuizFragment
import com.example.gch.fragment.CertifFragment
import android.util.Log

class MainActivity : AppCompatActivity(), HttpGetRequest.OnTaskCompleted {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFormation.setOnClickListener {
            // Make HTTP request when the Formation button is clicked
            val url = "https://192.168.104.196:3000/getAllformation"
            val getRequest = HttpGetRequest(this)
            getRequest.execute(url)

            // You can also pass data to the FormationFragment if needed
            val formationFragment = FormationFragment()
            val bundle = Bundle()
            bundle.putString("someKey", "someValue")
            formationFragment.arguments = bundle

            changeFragment(formationFragment, "")
        }

        binding.btnQuiz.setOnClickListener {
            // Make HTTP request when the Quiz button is clicked
//            val url = "http://your-nodejs-server-url/api/quiz"
//            val getRequest = HttpGetRequest(this)
//            getRequest.execute(url)

            changeFragment(QuizFragment(), "")
        }

        binding.btnAchievements.setOnClickListener {
            // Make HTTP request when the Achievements button is clicked
//            val url = "http://your-nodejs-server-url/api/achievements"
//            val getRequest = HttpGetRequest(this)
//            getRequest.execute(url)

            changeFragment(AchievementFragment(), "")
        }

        binding.btnCertif.setOnClickListener {
            // Make HTTP request when the Certif button is clicked
//            val url = "http://your-nodejs-server-url/api/certif"
//            val getRequest = HttpGetRequest(this)
//            getRequest.execute(url)

            changeFragment(CertifFragment(), "")
        }
    }

    override fun onTaskCompleted(result: String) {
        // Handle the result from the server
        // Update UI or perform any other actions
        Log.d("HTTP_RESPONSE", result ?: "Empty response")

//        binding.testText.text = result ?: "Empty response"
    }

    override fun onTaskError(error: Exception) {
        // Handle the error, log it, or perform any other actions
        Log.e("HTTP_ERROR", "Error during HTTP request", error)
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
