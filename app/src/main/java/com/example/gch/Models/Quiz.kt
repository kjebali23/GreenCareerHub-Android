package com.example.gch.Models

//class Quiz (
//    val id: Int,
//    val imageRes: Int,
//    val title: String,
//    val description: String,
//)


//data class Answer(
//    val text: String,
//    val isCorrect: Boolean
//)
//
//data class QuizQuestion(
//    val questionText: String,
//    val answers: List<Answer>
//)
//
//data class Quiz(
//    val id: Int,
//    val imageRes: Int,
//    val title: String,
//    val description: String,
//    val time: Int,
//    val questions: List<Question>
//)

// Modify Quiz class to use QuizQuestion
import android.os.Parcel
import android.os.Parcelable

data class QuizQuestion(
    val id: Int,
    val questionText: String,
    val answers: List<Answer>,
    val correctAnswer: List<Answer>? // Correct type declaration
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Answer.CREATOR) ?: emptyList(),
        parcel.createTypedArrayList(Answer.CREATOR) // Change this line
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(questionText)
        parcel.writeTypedList(answers)
        parcel.writeTypedList(correctAnswer) // Change this line
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuizQuestion> {
        override fun createFromParcel(parcel: Parcel): QuizQuestion {
            return QuizQuestion(parcel)
        }

        override fun newArray(size: Int): Array<QuizQuestion?> {
            return arrayOfNulls(size)
        }
    }
}

data class Quiz(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val description: String,
    val time: Int,
    val questions: List<QuizQuestion>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createTypedArrayList(QuizQuestion.CREATOR) ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(imageRes)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(time)
        parcel.writeTypedList(questions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Quiz> {
        override fun createFromParcel(parcel: Parcel): Quiz {
            return Quiz(parcel)
        }

        override fun newArray(size: Int): Array<Quiz?> {
            return arrayOfNulls(size)
        }
    }
}



// Add a new class for Answer
data class Answer(
    val text: String,
    val isCorrect: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun toString(): String {
        return text // Only return the text for a more readable format
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeByte(if (isCorrect) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Answer> {
        override fun createFromParcel(parcel: Parcel): Answer {
            return Answer(parcel)
        }

        override fun newArray(size: Int): Array<Answer?> {
            return arrayOfNulls(size)
        }
    }
}

