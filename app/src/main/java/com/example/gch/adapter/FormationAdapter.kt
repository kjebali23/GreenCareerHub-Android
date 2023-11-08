package com.example.gch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gch.Models.Formation
import com.example.gch.databinding.SingleFormationBinding

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
                binding.formationLevel.text = level
                binding.formationImage.setImageResource(imageRes)

            }
        }
    }

    override fun getItemCount() = formationList.size

    inner class FormationHolder(val binding: SingleFormationBinding) : RecyclerView.ViewHolder(binding.root)
}