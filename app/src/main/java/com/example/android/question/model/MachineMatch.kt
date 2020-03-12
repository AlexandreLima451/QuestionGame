package com.example.android.question.model

import android.content.Context
import com.example.android.question.R

class MachineMatch (context: Context) {

    //var animalResult = Results.noResult
    //var questions : MutableMap<String, QuestionModel> = hashMapOf()]
    private var questions : MutableList<QuestionModel> = mutableListOf()
    var currentQuestion = Questions.noQuestion
    var chosenAnimal = Animals.none
    private val applicationContext = context

    fun init(){
        //animalResult = Results.randomAnimal()
        chosenAnimal = Animals.randomAnimal()
        questions = loadQuestions()
    }

    fun reset(){
        init()
    }

    fun checkAnswer(result : ResultModel) : Boolean{
        return chosenAnimal.equals(result.animal)
    }

    fun answerQuestion(question : QuestionModel) : Boolean{
        var answer = false

        chosenAnimal.let {
            when(question.title){
                "mammal" -> answer = it.isMammal
                "quadruped" -> answer = it.isQuadruped
                "carnivore" -> answer = it.isCarnivore
                "herbivore" -> answer = it.isHerbivore
                "flying" -> answer = it.isFlying
                "fins" -> answer = it.hasFins
            }
        }
        return answer
    }

    fun loadQuestions() : MutableList<QuestionModel>{
        val questions = mutableListOf<QuestionModel>()
        questions.add(QuestionModel("mammal", applicationContext.getString(R.string.opt_quest_mammal)))
        questions.add(QuestionModel("herbivore", applicationContext.getString(R.string.opt_quest_herbivore)))
        questions.add(QuestionModel("quadruped", applicationContext.getString(R.string.opt_quest_quadruped)))
        questions.add(QuestionModel("carnivore", applicationContext.getString(R.string.opt_quest_carnivore)))
        questions.add(QuestionModel("fins", applicationContext.getString(R.string.opt_quest_fins)))
        questions.add(QuestionModel("flying", applicationContext.getString(R.string.opt_quest_flying)))

        return questions
    }

    fun getQuestions(): MutableList<QuestionModel> {
        return questions
    }

    fun removeQuestion(question : QuestionModel){
        questions.remove(question)
    }

    fun getQuantityOfQuestions() : Int{
        return questions.size
    }
}