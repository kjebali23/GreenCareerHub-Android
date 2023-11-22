package com.example.gch.Models

class Certif (
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val neededQuizzes : List<String>
//    val quizzes: List<Quiz>
) {
    override fun toString(): String {
        return "Certif(id=$id, title='$title', description='$description', imageUrl='$imageUrl', neededQuiz=$neededQuizzes)"
    }
}