package com.example.android.question.model

import com.example.android.question.R
import com.example.android.question.util.MainApplication

abstract class Questions {

    companion object {

        private var mammal = QuestionModel("mammal", MainApplication.applicationContext().resources.getString(R.string.question_mammal))
        private var quadruped = QuestionModel("quadruped", MainApplication.applicationContext().resources.getString(R.string.question_quadruped))
        private var carnivore = QuestionModel("carnivore", MainApplication.applicationContext().resources.getString(R.string.question_carnivore))
        private var herbivore = QuestionModel("herbivore", MainApplication.applicationContext().resources.getString(R.string.question_herbivore))
        private var flying = QuestionModel("flying", MainApplication.applicationContext().resources.getString(R.string.question_flying))
        private var fins = QuestionModel("fins", MainApplication.applicationContext().resources.getString(R.string.question_fins))
        var noQuestion = QuestionModel("null", "null")

        fun loadMainQuestions() : MutableMap<String, QuestionModel>{

            return mutableMapOf( "MAMMAL"    to mammal,
                                 "QUADRUPED" to quadruped,
                                 "CARNIVORE" to carnivore,
                                 "HERBIVORE" to herbivore)
        }

        fun loadSecondaryQuestions() : MutableMap<String, QuestionModel>{

            return mutableMapOf( "FLYING"    to flying, "FINS"      to fins)
        }

        fun loadAllQuestions() : MutableMap<String, QuestionModel>{
            return mutableMapOf( "MAMMAL"    to mammal,
                                "QUADRUPED" to quadruped,
                                "CARNIVORE" to carnivore,
                                "HERBIVORE" to herbivore,
                                "FLYING"    to flying,
                                "FINS"      to fins)
        }
    }

}