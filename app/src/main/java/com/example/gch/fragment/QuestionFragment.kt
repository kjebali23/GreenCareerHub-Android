package com.example.gch.fragment

// fragment/QuestionFragment.kt
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gch.MainActivity
import com.example.gch.Models.Participation
import com.example.gch.Models.Question
import com.example.gch.Models.QuizQuestion
import com.example.gch.adapter.QuestionAdapter
import com.example.gch.databinding.FragmentQuestionBinding
import com.example.gch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            // Fetch user's participation data
            fetchUserParticipation("khalil", "quiz_id", score)
        }

        return binding.root
    }

    private fun fetchUserParticipation(userId: String, quizId: String, score: Int) {
        val api = RetrofitClient.instance
        val call: Call<Participation> = api.getParticipation(userId, quizId)

        call.enqueue(object : Callback<Participation> {
            override fun onResponse(call: Call<Participation>, response: Response<Participation>) {
                if (response.isSuccessful) {
                    // Handle success, check if user has already passed the test
                    val participation = response.body()
                    if (participation != null && participation.passed) {
                        // User has already passed, show appropriate message or dialog
                        showAlreadyPassedDialog()
                    } else {
                        // User has not passed, proceed with submitting the new participation
                        submitParticipation(userId, quizId, score)
                    }
                } else {
                    // Handle failure, e.g., show an error message
                    Log.e("GetParticipation", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Participation>, t: Throwable) {
                // Handle failure, e.g., show an error message
                Log.e("GetParticipation", "Failure: ${t.message}")
            }
        })
    }

// Rest of the code...


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentQuestionBinding.inflate(layoutInflater)
//
//        // Initialize the adapter with the onAnswerSelected callback
//        questionAdapter = QuestionAdapter(getQuestionsFromArguments()) { position, answer ->
//            Log.e("Answer", "Question $position: $answer")
//        }
//
//        binding.rvQuestion.adapter = questionAdapter
//        binding.rvQuestion.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//
//        binding.btnSubmit.setOnClickListener {
//            val score = questionAdapter.calculateScore()
//            Log.e("Score ", score.toString())
//            // Do something with the score, e.g., show it in a Toast
//             Toast.makeText(requireContext(), "Your score: $score", Toast.LENGTH_SHORT).show()
//
//
//
//
//            val participation = Participation(
//                utilisateurId = "khalil",
//                quizName = "quiz_name",
//                quizId = "quiz_id",
//                score = score,
//                passed = score >= 2
//            )
//
//            val api = RetrofitClient.instance
//            val call: Call<Void> = api.addParticipation(participation)
//
//            call.enqueue(object : Callback<Void> {
//                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                    if (response.isSuccessful) {
//                        // Handle success, e.g., show a success message
//                        Log.d("AddParticipation", "Participation added successfully")
//                    } else {
//                        // Handle failure, e.g., show an error message
//                        Log.e("AddParticipation", "Error: ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<Void>, t: Throwable) {
//                    // Handle failure, e.g., show an error message
//                    Log.e("AddParticipation", "Failure: ${t.message}")
//                }
//            })
//
//            val alertDialogBuilder = AlertDialog.Builder(requireContext())
//            alertDialogBuilder.setTitle("Quiz Completed")
////            alertDialogBuilder.setMessage("Your score: $score")
//            alertDialogBuilder.setMessage("Your score: $score , You will be redirected to the main page")
//            alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
//                // Dismiss the dialog when the OK button is clicked
//                dialog.dismiss()
//
//                // Start the MainActivity
//                val intent = Intent(requireContext(), MainActivity::class.java)
//                startActivity(intent)
//                // Finish the current activity (QuestionFragment) if needed
//                activity?.finish()
//            }
//
//            alertDialogBuilder.create().show()
//
//        }
//
//        return binding.root
//    }

    private fun showAlreadyPassedDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Quiz Already Passed")
        alertDialogBuilder.setMessage("You have already passed the quiz.")
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            // Dismiss the dialog when the OK button is clicked
            dialog.dismiss()
            // Start the MainActivity
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            // Finish the current activity (QuestionFragment) if needed
            activity?.finish()
        }
        alertDialogBuilder.create().show()
    }

    private fun submitParticipation(userId: String, quizId: String, score: Int) {
        // Proceed with submitting the new participation
        val participation = Participation(
            utilisateurId = userId,
            quizName = "quiz_name",
            quizId = quizId,
            score = score,
            passed = score >= 2
        )

        val api = RetrofitClient.instance
        val call: Call<Void> = api.addParticipation(participation)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle success, e.g., show a success message
                    Log.d("AddParticipation", "Participation added successfully")
                } else {
                    // Handle failure, e.g., show an error message
                    Log.e("AddParticipation", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure, e.g., show an error message
                Log.e("AddParticipation", "Failure: ${t.message}")
            }
        })
    }



}