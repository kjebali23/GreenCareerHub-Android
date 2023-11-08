//package com.example.gch.fragment
//
//
//import android.content.Context
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.gch.databinding.FragmentFormationBinding
//
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.gch.Models.Formation
//import com.example.gch.R
//import com.example.gch.adapter.FormationAdapter
//import com.example.gch.databinding.FragmentQuizBinding
//import java.text.Format
//
//
//class QuizFragment: Fragment() {
//
//    private lateinit var binding: FragmentQuizBinding
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        binding = FragmentQuizBinding.inflate(layoutInflater)
//
//        binding.rvQuiz.adapter = FormationAdapter(getListNews(requireContext()))
//        binding.rvQuiz.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//
//        return binding.root
//    }
//
//
//    private fun getListNews(context: Context) : MutableList<Formation> {
//        return mutableListOf(
//            Formation(1, R.drawable.ic_news1, context.getString(R.string.news1) , context.getString(R.string.newsDesc1)),
//            Formation(2, R.drawable.ic_news1, context.getString(R.string.news2) , context.getString(R.string.newsDesc2)),
//            Formation(3, R.drawable.ic_news1, context.getString(R.string.news3) , context.getString(R.string.newsDesc3))
//        )
//    }
//
//
//}


package com.example.gch.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gch.R
import com.example.gch.Models.Quiz
import com.example.gch.adapter.GridViewAdapter
import com.example.gch.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)

        val gridView = binding.gvQuiz // Update the view reference

        gridView.adapter = GridViewAdapter(requireContext(), getListNews(requireContext()))
        // gridView is your GridView, and you're using the GridViewAdapter

        return binding.root
    }

    private fun getListNews(context: Context): List<Quiz> {
        return listOf(
            Quiz(1, R.drawable.ic_news1, context.getString(R.string.news1), context.getString(R.string.news1)),
            Quiz(2, R.drawable.ic_news1, context.getString(R.string.news1), context.getString(R.string.news2)),
            Quiz(3, R.drawable.ic_news1, context.getString(R.string.news1), context.getString(R.string.news3))
        )
    }
}
