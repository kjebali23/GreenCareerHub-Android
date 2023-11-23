package com.example.gch

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.gch.Models.Formation
import com.example.gch.databinding.AddFormationBinding
import com.example.gch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    private lateinit var binding: AddFormationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddFormationBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnAdd.setOnClickListener {
            addFormation()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, EntrepriseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addFormation() {
        val logo = binding.frlogo.toString()
        val title = binding.frtitle.text.toString()
        val desc = binding.idDesc.text.toString()
        val lien = binding.frlien.text.toString()



        val newFormation = Formation(
            id = "0",
            imageUrl = logo,
            title = title,
            description = desc,
            formationUrl = lien,
            imageRes = 0,
            level = "test",
            category = "test",
            addedBy = "khalil"
        )

        Log.d("Formation", "ID: ${newFormation.id}")
        Log.d("Formation", "Image URL: ${newFormation.imageUrl}")
        Log.d("Formation", "Title: ${newFormation.title}")
        Log.d("Formation", "Description: ${newFormation.description}")
        Log.d("Formation", "Formation URL: ${newFormation.formationUrl}")
        Log.d("Formation", "Image Resource: ${newFormation.imageRes}")



        val api = RetrofitClient.instance
        val call: Call<Formation> = api.addFormation(newFormation)
        Log.d("RetrofitRequest", "Request Body: ${call.request()}")

        call.enqueue(object : Callback<Formation> {
            override fun onResponse(call: Call<Formation>, response: Response<Formation>) {
                Log.d("AddFormationActivity", "onResponse: ${response.body()}")

                if (response.isSuccessful) {
                    Log.d("AddFormationActivity", "Formation added successfully")
                } else {
                    if (response.errorBody() != null) {
                        Log.e("AddFormationActivity", "Error: ${response.errorBody()?.string()}")
                    } else {
                        Log.e("AddFormationActivity", "Error: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<Formation>, t: Throwable) {
                // Gestion des erreurs en cas d'échec de la requête
                Log.e("AddFormationActivity", "Failure: ${t.message}")
            }
        })
    }
}