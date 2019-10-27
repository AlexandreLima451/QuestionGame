package com.example.android.question.model

import java.util.*

/**
 * This class handles with the match's logic
 */
class Match {

    var currentQuestion = Questions.noQuestion
    private var currentAnswer = false
    private var questions : MutableMap<String, QuestionModel> = hashMapOf()
    private var results : MutableMap<String, ResultModel> = hashMapOf()
    private var finalResult = ResultModel(Results.none, "")

    /**
     * This method initializes the match
     * */
    fun init(){
        questions = Questions.loadMainQuestions()
        results = Results.newInstance()
        loadQuestion()
        currentAnswer = false
        finalResult = ResultModel(Results.none, "")
    }

    /**
     * This method tries to figure out which animal was chosen
     * @return true if it found a correct result
     * */
    fun checkResult() : Boolean{
        if (questions.isEmpty()){
            results.forEach {
                if(finalResult.animal.equals (it.value.animal)){
                    finalResult = it.value
                    return true
                }
            }
        }
        return false
    }

    /**
     * This method finishes the current match
     * @return result's message
     * */
    fun finish() : String{
        val finalText = finalResult.resultText
        questions.clear()
        results.clear()
        return finalText
    }

    /**
     * This method resets the game
     * */
    fun reset(){
        init()
    }

    /**
     * This method loads a new question
     */
    fun loadQuestion() : Boolean{
        if(questions.isEmpty()) {
            if (currentQuestion.title != "flying" && currentQuestion.title != "fins") {
                questions = Questions.loadSecondaryQuestions()
            } else {
                finish()
                return false
            }
        }
        val questMap = questions.getRandomly()
        currentQuestion.title = questMap.value.title
        currentQuestion.text = questMap.value.text

        questions.remove(questMap.key) // remove item to not repeat the question again
        return true
    }

    /**
     * This method sets the answer
     * @param answer chosen by the player
     * */
    fun setAnswer(answer : Boolean){
        when(currentQuestion.title){
            "mammal" -> finalResult.animal.isMammal = answer
            "quadruped" -> finalResult.animal.isQuadruped = answer
            "carnivore" -> finalResult.animal.isCarnivore = answer
            "herbivore" -> finalResult.animal.isHerbivore = answer
            "flying" -> finalResult.animal.isFlying = answer
            "fins" -> finalResult.animal.hasFins = answer
        }
    }

    fun Map<String, QuestionModel>.getRandomly() : MutableMap.MutableEntry <String, QuestionModel> {
        val random = Random()
        val questionMap = questions.entries.elementAt(random.nextInt(questions.size))
        return questionMap
    }
}