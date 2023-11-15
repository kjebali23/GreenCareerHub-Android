package com.example.gch.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.Models.Formation
import com.example.gch.R
import com.example.gch.adapter.CertifAdapter
import com.example.gch.adapter.FormationAdapter
import com.example.gch.databinding.FragmentCertifBinding

class CertifFragment: Fragment() {

    lateinit var binding: FragmentCertifBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCertifBinding.inflate(layoutInflater)



        binding.rvCertif.adapter = CertifAdapter(getListCertif(requireContext()))
        binding.rvCertif.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    private fun getListCertif(context: Context) : MutableList<Formation> {
        return mutableListOf(
            Formation(1, R.drawable.img_rectangle1314, context.getString(R.string.news1) , context.getString(
                R.string.newsDesc1), context.getString(R.string.dificulty1)),
            Formation(2, R.drawable.img_rectangle1314_2, context.getString(R.string.news2) , context.getString(
                R.string.newsDesc2) , context.getString(R.string.dificulty1)),
            Formation(3, R.drawable.img_rectangle1314_4, context.getString(R.string.news3) , context.getString(
                R.string.newsDesc3) , context.getString(R.string.dificulty1))
        )
    }


}