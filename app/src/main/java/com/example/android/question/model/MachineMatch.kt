package com.example.android.question.model

class MachineMatch {

    var animalResult = Results.randomAnimal()
    var questions : MutableMap<String, QuestionModel> = hashMapOf()
    var currentQuestion = Questions.noQuestion

    fun init(){
        animalResult = Results.randomAnimal()
        questions = Questions.loadAllQuestions()
    }

    fun reset(){
        init()
    }
}