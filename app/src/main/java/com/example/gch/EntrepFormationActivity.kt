package com.example.gch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gch.databinding.ActivityCourseBinding
import com.example.gch.databinding.ActivityFormationDetailBinding
import com.example.gch.network.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntrepFormationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormationDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageArrowleft.setOnClickListener{
            val intent = Intent( this , MainActivity::class.java).apply {
//                putExtra("test", "test")
            }
            this.startActivity(intent)
        }






        val imageRes = intent.getIntExtra("image", 0)
        val title = intent.getStringExtra("title") ?: "" // Use an empty string if title is null
        val description = intent.getStringExtra("description")
        val formatioUrl = intent.getStringExtra("formationUrl")

        val imageView = findViewById<ImageView>(R.id.courseimage)
        imageView.setImageResource(imageRes)

        val textView = findViewById<TextView>(R.id.coursetitle)
        textView.text = title

        val descriptionTextView = findViewById<TextView>(R.id.txtDescription)
        descriptionTextView.text = description

        binding.buttonUpdate.setOnClickListener {
            Log.d("UpdateActivity", "Button clicked") // Add this log statement
            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra("title", title)
            }
            startActivity(intent)
        }



        binding.button.setOnClickListener {
            val api = RetrofitClient.instance
            val call: Call<Void> = api.deleteFormation(title)
            Log.e("title fel remove", title)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Handle success
                        Log.d("DeleteFormation", "Formation deleted successfully")
                    } else {
                        // Handle failure
                        Log.e("DeleteFormation", "Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Handle failure
                    Log.e("DeleteFormation", "Failure: ${t.message}")
                }
            })
            val intent = Intent(this, EntrepriseActivity::class.java)
            startActivity(intent)
        }




    }

}
