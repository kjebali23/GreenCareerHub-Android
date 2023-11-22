package com.example.gch.adapter

import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.gch.CertifActivity
import com.example.gch.CourseActivity
import com.example.gch.Models.Certif
import com.example.gch.Models.Formation
import com.example.gch.R
import com.example.gch.databinding.SingleCertifBinding
import com.squareup.picasso.Picasso

class CertifAdapter(val certifList: MutableList<Certif>) : RecyclerView.Adapter<CertifAdapter.CertifHolder>() {

    private var neededQuizList: List<String> = emptyList()

    fun setNeededQuizList(neededQuizList: List<String>) {
        this.neededQuizList = neededQuizList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertifAdapter.CertifHolder {
        val binding = SingleCertifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CertifHolder(binding)
    }

    override fun onBindViewHolder(holder: CertifAdapter.CertifHolder, position: Int) {
        with(holder){
            with(certifList[position]){
                binding.certifTitle.text = title


//                  Using Picasso
                Picasso.get().load("https://palestinevaincra.com/site/wp-content/uploads/2020/11/visuel-FB-Boycott.png?fbclid=IwAR0KNeII3PPWJZbtaYAEpBSL-cEZt9PKP3fsiTUa0YMSYAwaXzxUj9VTjSs.png")
                    .placeholder(R.drawable.noimage).into(binding.certifImage)



//                Glide.with(itemView.context)
//                    .load("https://palestinevaincra.com/site/wp-content/uploads/2020/11/visuel-FB-Boycott.png")
//                    .error(R.drawable.noimage)
//                    .into(binding.certifImage)




//                Log.d("CertifActivity", "Needed Quiz: $neededQuiz")
//
//                binding.actionShowMore.setOnClickListener{
//
//                    val intent = Intent( itemView.context , CertifActivity::class.java).apply {
////                        putExtra("image", imageUrl)
//                        putExtra("title" , title)
//                        putExtra("Description" , description)
//
//                    }
//                    itemView.context.startActivity(intent)
//
//                }
                binding.actionShowMore.setOnClickListener{
                    val intent = Intent(itemView.context, CertifActivity::class.java).apply {
                        putExtra("title", title)

                    }
                    itemView.context.startActivity(intent)
                }


            }
        }
    }




    fun updateList(newList: List<Certif>) {
        certifList.clear()
        certifList.addAll(newList)
        notifyDataSetChanged()
    }




    override fun getItemCount() = certifList.size

    inner class CertifHolder(val binding: SingleCertifBinding) : RecyclerView.ViewHolder(binding.root)
}


