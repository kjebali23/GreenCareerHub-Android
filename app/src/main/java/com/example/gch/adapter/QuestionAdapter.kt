package com.example.gch.adapter



import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gch.Models.Answer
import com.example.gch.Models.QuizQuestion
import com.example.gch.databinding.SingleQuestionBinding

class QuestionAdapter(private val questionList: List<QuizQuestion>, private val onAnswerSelected: (Int, String) -> Unit) :
    RecyclerView.Adapter<QuestionAdapter.QuestionHolder>() {

    private val userAnswers = mutableMapOf<Int, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val binding = SingleQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        val question = questionList[position]
        holder.bind(question, userAnswers[position])
    }

    override fun getItemCount() = questionList.size

    inner class QuestionHolder(private val binding: SingleQuestionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(question: QuizQuestion, userAnswer: String?) {
            binding.textViewQuestion.text = question.questionText
            binding.radioButton1.text = question.answers[0].toString()
            binding.radioButton2.text = question.answers[1].toString()
            binding.radioButton3.text = question.answers[2].toString()

            binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val selectedAnswer = when (checkedId) {
                    binding.radioButton1.id -> question.answers[0]
                    binding.radioButton2.id -> question.answers[1]
                    binding.radioButton3.id -> question.answers[2]
                    else -> null
                }
                selectedAnswer?.let {
                    userAnswers[adapterPosition] = it.text
                    onAnswerSelected(adapterPosition, it.text)
                }
            }
        }
    }

    fun calculateScore(): Int {
        var score = 0
        for ((position, userAnswerText) in userAnswers) {
            val userAnswer = Answer(userAnswerText, false) // Create an Answer object from the user's answer text
            val correctAnswer = questionList[position].correctAnswer
            if (correctAnswer != null && userAnswer.text == correctAnswer.text && correctAnswer.isCorrect) {
                score++
            }
        }
        return score
    }









}






//import com.example.gch.Models.Question
//
//import android.app.Activity
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.gch.databinding.SingleQuestionBinding
//import com.google.android.material.snackbar.Snackbar

//class QuestionAdapter(val questionList: MutableList<Question>) : RecyclerView.Adapter<QuestionAdapter.QuestionHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
//        val binding = SingleQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return QuestionHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
//        with(holder){
//            with(questionList[position]){
////                binding.newsTitle.text = title
////                binding.newsDescription.text = description
////                binding.newsImage.setImageResource(imageRes)
////                binding.actionShowMore.setOnClickListener {
//////                    Snackbar.make((itemView.context as Activity).findViewById(R.id.context_view), itemView.context.getString(R.string.msg_coming_soon), Snackbar.LENGTH_SHORT).show()
////                }
//            }
//        }
//    }
//
//    override fun getItemCount() = questionList.size
//
//    inner class QuestionHolder(val binding: SingleQuestionBinding) : RecyclerView.ViewHolder(binding.root)
//}