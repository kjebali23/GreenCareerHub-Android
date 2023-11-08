package com.example.gch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.databinding.AchievementsFragmentBinding



class AchievementFragment : Fragment(){

    private lateinit var binding: AchievementsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AchievementsFragmentBinding.inflate(layoutInflater)

        //TODO 14 Get all events from database and create the BookmarksAdapter and assign it to the recyclerView rvBookmarks

        binding.rvBookmarks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}