package com.example.gch.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gch.CourseActivity
import com.example.gch.Models.Formation
import com.example.gch.databinding.SingleCertifBinding

class CertifAdapter(val formationList: MutableList<Formation>) : RecyclerView.Adapter<CertifAdapter.CertifHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertifAdapter.CertifHolder {
        val binding = SingleCertifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CertifHolder(binding)
    }

    override fun onBindViewHolder(holder: CertifAdapter.CertifHolder, position: Int) {
        with(holder){
            with(formationList[position]){
                binding.certifTitle.text = title
//                binding.formationDescription.text = description
//                binding.formationLevel.text = level
                binding.certifImage.setImageResource(imageRes)
                binding.actionShowMore.setOnClickListener{
//                    Snackbar.make((itemView.context as Activity).findViewById(R.id.context_view), itemView.context.getString(R.string.msg_coming_soon), Snackbar.LENGTH_SHORT).show()

                    val intent = Intent( itemView.context , CourseActivity::class.java).apply {
                        putExtra("image", imageRes)
                        putExtra("title" , title)

                    }
                    itemView.context.startActivity(intent)

                }


            }
        }
    }








    override fun getItemCount() = formationList.size

    inner class CertifHolder(val binding: SingleCertifBinding) : RecyclerView.ViewHolder(binding.root)
}


