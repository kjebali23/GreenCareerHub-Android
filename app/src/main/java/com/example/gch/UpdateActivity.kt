package com.example.gch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gch.Models.Formation
import com.example.gch.databinding.UpdateFormationBinding
import com.example.gch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: UpdateFormationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateFormationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize your views and set any necessary data

        binding.btnAdd.setOnClickListener {
            updateFormation()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, EntrepriseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateFormation() {
        // Retrieve data from your views
        val formationId = "placeholder"
        val updatedLogo = binding.frlogo.text.toString()
        val updatedTitle = binding.frtitle.text.toString()
        val updatedDesc = binding.description.text.toString()
        val updatedLien = binding.frlien.text.toString()

        // Create a Formation object with the updated data
        val updatedFormation = Formation(
            id = formationId,
            imageUrl = updatedLogo,
            title = updatedTitle,
            description = updatedDesc,
            formationUrl = updatedLien,
            imageRes = 0, // You may update this if needed
            level = "test", // You may update this if needed
            category = "test", // You may update this if needed
            addedBy = "khalil" // You may update this if needed
        )

        // Make the Retrofit call to update the Formation
        val api = RetrofitClient.instance
        val call: Call<Formation> = api.updateFormation(updatedFormation)

        call.enqueue(object : Callback<Formation> {
            override fun onResponse(call: Call<Formation>, response: Response<Formation>) {
                if (response.isSuccessful) {
                    Log.d("UpdateFormationActivity", "Formation updated successfully")
                } else {
                    if (response.errorBody() != null) {
                        Log.e("UpdateFormationActivity", "Error: ${response.errorBody()?.string()}")
                    } else {
                        Log.e("UpdateFormationActivity", "Error: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<Formation>, t: Throwable) {
                Log.e("UpdateFormationActivity", "Failure: ${t.message}")
            }
        })
    }
}


//class UpdateActivity : AppCompatActivity() {
//
//    private lateinit var binding: UpdateFormationBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = UpdateFormationBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Initialize your views and set any necessary data
//
//        binding.btnAdd.setOnClickListener {
//            updateFormation()
//        }
//
//        binding.btnBack.setOnClickListener {
//            val intent = Intent(this, EntrepriseActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun updateFormation() {
//        // Retrieve data from your views
//        val formationId = "placeholder"
//        val updatedLogo = binding.frlogo.text.toString()
//        val updatedTitle = binding.frtitle.text.toString()
//        val updatedDesc = binding.description.text.toString()
//        val updatedLien = binding.frlien.text.toString()
//
//        // Create a Formation object with the updated data
//        val updatedFormation = Formation(
//            id = formationId,
//            imageUrl = updatedLogo,
//            title = updatedTitle,
//            description = updatedDesc,
//            formationUrl = updatedLien,
//            imageRes = 0, // You may update this if needed
//            level = "test", // You may update this if needed
//            category = "test", // You may update this if needed
//            addedBy = "khalil" // You may update this if needed
//        )
//
//        // Make the Retrofit call to update the Formation
//        val api = RetrofitClient.instance
//        val call: Call<Formation> = api.updateFormation(formationId, updatedFormation)
//
//        call.enqueue(object : Callback<Formation> {
//            override fun onResponse(call: Call<Formation>, response: Response<Formation>) {
//                if (response.isSuccessful) {
//                    Log.d("UpdateFormationActivity", "Formation updated successfully")
//                } else {
//                    if (response.errorBody() != null) {
//                        Log.e("UpdateFormationActivity", "Error: ${response.errorBody()?.string()}")
//                    } else {
//                        Log.e("UpdateFormationActivity", "Error: ${response.message()}")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<Formation>, t: Throwable) {
//                Log.e("UpdateFormationActivity", "Failure: ${t.message}")
//            }
//        })
//    }
//}
//
//
