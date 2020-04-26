package com.example.android.question.model.domain

import com.example.android.question.model.ResultModel

abstract class Results {

    companion object {

        val noResult = ResultModel(Animals.none, "Acho que não consegui pensar direito")
    }
}