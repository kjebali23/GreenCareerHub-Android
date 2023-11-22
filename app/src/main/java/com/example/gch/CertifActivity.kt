package com.example.gch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gch.Models.Certif
import com.example.gch.adapter.GridViewAdapter
import com.example.gch.databinding.ActivityCertifBinding
import com.example.gch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CertifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCertifBinding
    private lateinit var quizAdapter: GridViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCertifBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageArrowleft.setOnClickListener{
            val intent = Intent( this , MainActivity::class.java).apply {
//                putExtra("test", "test")
            }
            this.startActivity(intent)
        }

        val title = intent.getStringExtra("title")
        val textView = findViewById<TextView>(R.id.certifTitle)
        textView.text = title

        if (title != null) {
            fetchCertifByTitle(title)
        }








        quizAdapter = GridViewAdapter(this, emptyList()) { quiz ->
            // Handle quiz item click if needed
        }

        // Initialize your quiz adapter

        // Set the adapter to the GridView
        binding.gvQuiz.adapter = quizAdapter

    }

    private fun fetchCertifByTitle(title: String) {
        val api = RetrofitClient.instance
        val call = api.getCertifByTitle(title)

        call.enqueue(object : Callback<Certif> {
            override fun onResponse(call: Call<Certif>, response: Response<Certif>) {
                if (response.isSuccessful) {
                    val certif = response.body()
                    Log.e("certif", certif.toString())

                    if (certif != null) {
                        val descriptionTextView = findViewById<TextView>(R.id.txtDescription)
                        descriptionTextView.text = certif.description
                    }else{
                        Log.e("description","error")
                    }

                } else {
                    Log.e("error", "error")
                }
            }

            override fun onFailure(call: Call<Certif>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
    }



}

