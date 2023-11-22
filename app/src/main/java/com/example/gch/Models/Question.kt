package com.example.gch.Models

// Models/Question.kt
data class Question(
    val id: Int,
    val title: String,
//    val description: String,
    val answers: Array<String>,
    val correctAnswer: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (id != other.id) return false
        if (title != other.title) return false
        if (!answers.contentEquals(other.answers)) return false
        if (correctAnswer != other.correctAnswer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + answers.contentHashCode()
        result = 31 * result + correctAnswer.hashCode()
        return result
    }
}
