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
import com.example.gch.Models.Formation
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), HttpGetRequest.OnTaskCompleted {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFormation.setOnClickListener {
            // Make HTTP request when the Formation button is clicked
            val url = "http://172.16.5.212:3000/getAllformation"
            val getRequest = HttpGetRequest(this)
            getRequest.execute(url)

            // You can also pass data to the FormationFragment if needed
            val formationFragment = FormationFragment()
//            val bundle = Bundle()
//            bundle.putString("formations", formationList)
//            formationFragment.arguments = bundle

            changeFragment(formationFragment, "")
        }

        binding.btnQuiz.setOnClickListener {
//             Make HTTP request when the Quiz button is clicked
            val url = "http://172.16.5.212:3000/getAllquizes"
            val getRequest = HttpGetRequest(this)
            getRequest.execute(url)

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
//        // Handle the result from the server
//        val formationList = Gson().fromJson<List<Formation>>(result)
//        // Update UI or perform any other actions
//        Log.d("HTTP_RESPONSE", result ?: "Empty response")
//
////        binding.testText.text = result ?: "Empty response"

        if (!isValidJSON(result)) {
            Log.e("Error", "Invalid JSON data")
            return
        }

        try {
            val jsonElement = JsonParser.parseString(result)
            val formationList: List<Formation> = Gson().fromJson(jsonElement, object : TypeToken<List<Formation>>() {}.type)
            // Update UI or perform any other actions
            Log.d("HTTP_RESPONSE", result ?: "Empty response")

            for (formation in formationList) {
                Log.d("Formation", "------------------------------")
                Log.d("Formation ID", formation.id.toString())
                Log.d("Formation Name", formation.title)
                Log.d("Formation Description", formation.description)
//                Log.d("Formation Image URL", formation.imageUrl)
            }



            // Use formationList to update UI or perform actions
        } catch (e: Exception) {
            Log.e("Error", "Failed to parse JSON data: ${e.message}")
        }

    }

    private fun isValidJSON(jsonString: String): Boolean {
        try {
            JsonParser.parseString(jsonString)
            return true
        } catch (e: Exception) {
            return false
        }
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
