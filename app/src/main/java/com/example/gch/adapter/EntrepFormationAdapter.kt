package com.example.gch.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gch.CourseActivity
import com.example.gch.EntrepFormationActivity
import com.example.gch.MainActivity
import com.example.gch.Models.Formation
import com.example.gch.R
import com.example.gch.databinding.SingleFormationBinding
import com.squareup.picasso.Picasso


class EntrepFormationAdapter(val formationList: MutableList<Formation>) : RecyclerView.Adapter<EntrepFormationAdapter.FormationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormationHolder {
        val binding = SingleFormationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FormationHolder(binding)
    }

    override fun onBindViewHolder(holder: FormationHolder, position: Int ) {
        with(holder){
            with(formationList[position]){
                binding.formationTitle.text = title


                Picasso.get().load(imageUrl)
                    .placeholder(R.drawable.noimage)
                    .into(binding.formationImage)

//                binding.formationImage.setImageResource(imageRes)
                binding.actionShowMore.setOnClickListener{

                    val intent = Intent( itemView.context , EntrepFormationActivity::class.java).apply {
                        putExtra("image", imageUrl)
                        putExtra("title" , title)
                        putExtra("description" , description)
                        putExtra("formationUrl" , formationUrl)

                    }
                    itemView.context.startActivity(intent)



                }


            }
        }
    }

    override fun getItemCount() = formationList.size

    fun updateList(newList: List<Formation>) {
        formationList.clear()
        formationList.addAll(newList)
        notifyDataSetChanged()
        Log.d("formation List", formationList[0].imageUrl)
    }

    inner class FormationHolder(val binding: SingleFormationBinding) : RecyclerView.ViewHolder(binding.root)
}