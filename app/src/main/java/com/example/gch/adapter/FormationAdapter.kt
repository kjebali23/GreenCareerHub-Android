package com.example.gch.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gch.Models.Formation
import com.example.gch.R
import com.example.gch.databinding.SingleFormationBinding
import android.content.Context
import android.content.Intent
import com.example.gch.CourseActivity
import com.example.gch.MainActivity
import com.google.android.material.snackbar.Snackbar


class FormationAdapter(val formationList: MutableList<Formation>) : RecyclerView.Adapter<FormationAdapter.FormationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormationHolder {
        val binding = SingleFormationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FormationHolder(binding)
    }

    override fun onBindViewHolder(holder: FormationHolder, position: Int) {
        with(holder){
            with(formationList[position]){
                binding.formationTitle.text = title
//                binding.formationDescription.text = description
//                binding.formationLevel.text = level
                binding.formationImage.setImageResource(imageRes)
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

    inner class FormationHolder(val binding: SingleFormationBinding) : RecyclerView.ViewHolder(binding.root)
}