package com.example.gch.fragment

// fragment/QuestionFragment.kt
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.Models.Question
import com.example.gch.Models.QuizQuestion
import com.example.gch.adapter.QuestionAdapter
import com.example.gch.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private lateinit var questionAdapter: QuestionAdapter
    private var questionList: List<Question>? = null

    private fun getQuestionsFromArguments(): List<QuizQuestion> {
        return arguments?.getParcelableArrayList<QuizQuestion>("questions") ?: emptyList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(layoutInflater)

        // Initialize the adapter with the onAnswerSelected callback
        questionAdapter = QuestionAdapter(getQuestionsFromArguments()) { position, answer ->
            Log.e("Answer", "Question $position: $answer")
        }

        binding.rvQuestion.adapter = questionAdapter
        binding.rvQuestion.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.btnSubmit.setOnClickListener {
            val score = questionAdapter.calculateScore()
            Log.e("Score ", score.toString())
            // Do something with the score, e.g., show it in a Toast
             Toast.makeText(requireContext(), "Your score: $score", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


}