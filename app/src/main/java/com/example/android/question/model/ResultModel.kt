package com.example.android.question.model

import com.example.android.question.model.domain.Animal
import java.io.Serializable

data class ResultModel (var animal : Animal, var resultText : String) : Serializable