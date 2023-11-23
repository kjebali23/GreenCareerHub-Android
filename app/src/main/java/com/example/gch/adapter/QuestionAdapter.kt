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
            val correctAnswers = questionList[position].correctAnswer

            if (correctAnswers != null && correctAnswers.any { it.text == userAnswer.text && it.isCorrect }) {
                score++
            }
        }
        return score
    }










}





