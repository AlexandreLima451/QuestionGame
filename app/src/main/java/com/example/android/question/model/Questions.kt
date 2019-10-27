package com.example.android.question.model

import com.example.android.question.R
import com.example.android.question.util.MainApplication

abstract class Questions {

    companion object {

        var mammal = QuestionModel("mammal", MainApplication.applicationContext().resources.getString(R.string.question_mammal))
        var quadruped = QuestionModel("quadruped", MainApplication.applicationContext().resources.getString(R.string.question_quadruped))
        var carnivore = QuestionModel("carnivore", MainApplication.applicationContext().resources.getString(R.string.question_carnivore))
        var herbivore = QuestionModel("herbivore", MainApplication.applicationContext().resources.getString(R.string.question_herbivore))
        var flying = QuestionModel("flying", MainApplication.applicationContext().resources.getString(R.string.question_fins))
        var fins = QuestionModel("fins", MainApplication.applicationContext().resources.getString(R.string.question_flying))
        var noQuestion = QuestionModel("null", "null")

        fun loadMainQuestions() : MutableMap<String, QuestionModel>{

            val list = mutableMapOf<String, QuestionModel>( "MAMMAL"    to mammal,
                                                            "QUADRUPED" to quadruped,
                                                            "CARNIVORE" to carnivore,
                                                            "HERBIVORE" to herbivore )

            return list
        }

        fun loadSecondaryQuestions() : MutableMap<String, QuestionModel>{

            val list = mutableMapOf<String, QuestionModel>( "FLYING"    to flying,
                                                            "FINS"      to fins        )

            return list
        }
    }

}