package com.example.android.question.model

import android.content.res.Resources
import com.example.android.question.R

abstract class Questions {

    companion object {

        var mammal = QuestionModel("mammal", Resources.getSystem().getString(R.string.question_mammal))
        var quadruped = QuestionModel("quadruped", Resources.getSystem().getString(R.string.question_quadruped))
        var carnivore = QuestionModel("carnivore", Resources.getSystem().getString(R.string.question_carnivore))
        var herbivore = QuestionModel("herbivore", Resources.getSystem().getString(R.string.question_herbivore))
        var flying = QuestionModel("flying", Resources.getSystem().getString(R.string.question_flying))
        var fins = QuestionModel("fins", Resources.getSystem().getString(R.string.question_fins))
        var noQuestion = QuestionModel("null", "null")

        val list = mutableMapOf<String, QuestionModel>( "MAMMAL"    to mammal,
                                                        "QUADRUPED" to quadruped,
                                                        "CARNIVORE" to carnivore,
                                                        "HERBIVORE" to herbivore,
                                                        "FLYING"    to flying,
                                                        "FINS"      to fins        )

        fun newInstance() : MutableMap<String, QuestionModel>{
            return list
        }
    }

}