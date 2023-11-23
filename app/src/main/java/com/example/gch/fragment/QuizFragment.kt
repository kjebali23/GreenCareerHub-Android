//
//
//package com.example.gch.fragment
//
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.gch.Models.Formation
//import com.example.gch.Models.Question
//import com.example.gch.R
//import com.example.gch.Models.Quiz
//import com.example.gch.adapter.GridViewAdapter
//import com.example.gch.databinding.FragmentQuizBinding
//import com.example.gch.network.RetrofitClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//
//
//
//class QuizFragment : Fragment() {
//
//    private lateinit var binding: FragmentQuizBinding
//    private lateinit var quizAdapter: GridViewAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentQuizBinding.inflate(layoutInflater)
//
//        val gridView = binding.gvQuiz
//        quizAdapter = GridViewAdapter(requireContext(), mutableListOf()) // Initialize with an empty list
//        gridView.adapter = quizAdapter
//
//        fetchDataFromApi()
//
//        return binding.root
//    }
//
//    private fun fetchDataFromApi() {
//        val api = RetrofitClient.instance
//        val call = api.getQuizList()
//
//        call.enqueue(object : Callback<List<Quiz>> {
//            override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    Log.e("FRAGMENT Quiz", responseBody.toString())
//
//                    if (responseBody != null) {
//                        quizAdapter.updateList(responseBody)
//                    } else {
//                        Log.e("FRAGMENT Quiz", "Response body is null")
//                    }
//                } else {
//                    Log.d("aaaaaaa", (response ?: "Empty response").toString())
//                }
//            }
//
//            override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
//                Log.e("onFailure", t.toString())
//            }
//        })
//    }
//}


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gch.Models.Quiz
import com.example.gch.R
import com.example.gch.adapter.GridViewAdapter
import com.example.gch.databinding.FragmentQuizBinding
import com.example.gch.fragment.QuestionFragment
import com.example.gch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var quizAdapter: GridViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)

        val gridView = binding.gvQuiz
        quizAdapter = GridViewAdapter(requireContext(), mutableListOf()) { quiz ->
            // Handle quiz item click
            showQuestionsFragment(quiz)
        }
        gridView.adapter = quizAdapter

        fetchDataFromApi()

        return binding.root
    }

    private fun fetchDataFromApi() {
        val api = RetrofitClient.instance
        val call = api.getQuizList()

        call.enqueue(object : Callback<List<Quiz>> {
            override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.e("FRAGMENT Quiz", responseBody.toString())

                    if (responseBody != null) {
                        quizAdapter.updateList(responseBody)
                    } else {
                        Log.e("FRAGMENT Quiz", "Response body is null")
                    }
                } else {
                    Log.d("Error", (response ?: "Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
    }

    private fun showQuestionsFragment(quiz: Quiz) {
        val questionsFragment = QuestionFragment()

        // Pass the selected quiz data to the QuestionFragment
        val bundle = Bundle().apply {
            putParcelableArrayList("questions", ArrayList(quiz.questions))
        }
        questionsFragment.arguments = bundle

        // Replace the current fragment with the QuestionFragment
        val transaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.fragment_container, questionsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}




