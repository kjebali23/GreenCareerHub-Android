package com.example.gch.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gch.databinding.FragmentFormationBinding

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.Models.Formation
import com.example.gch.R
import com.example.gch.adapter.FormationAdapter
import java.text.Format


class FormationFragment: Fragment() {

    private lateinit var binding: FragmentFormationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFormationBinding.inflate(layoutInflater)

        binding.rvFormation.adapter = FormationAdapter(getListNews(requireContext()))
        binding.rvFormation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }


    private fun getListNews(context: Context) : MutableList<Formation> {
        return mutableListOf(
            Formation(1, R.drawable.ic_news1, context.getString(R.string.news1) , context.getString(R.string.newsDesc1), context.getString(R.string.dificulty1)),
            Formation(2, R.drawable.ic_news2, context.getString(R.string.news2) , context.getString(R.string.newsDesc2) , context.getString(R.string.dificulty1)),
            Formation(3, R.drawable.ic_news3, context.getString(R.string.news3) , context.getString(R.string.newsDesc3) , context.getString(R.string.dificulty1))
        )
    }


}