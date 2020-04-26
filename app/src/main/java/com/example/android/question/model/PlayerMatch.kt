package com.example.android.question.model

import android.content.Context
import com.example.android.question.R
import com.example.android.question.model.domain.Animals
import com.example.android.question.model.domain.Questions
import java.util.*

/**
 * This class handles with the player match's logic
 */
class PlayerMatch(context: Context) {

    private val applicationContext = context
    var currentQuestion = Questions.noQuestion
    private var currentAnswer = false
    private var questions : MutableMap<String, QuestionModel> = hashMapOf()
    private var results : MutableMap<String, ResultModel> = hashMapOf()
    private var finalResult = ResultModel(Animals.none, "")
    private var isMatchRunning = false
    private var totalQuestions = 0

    /**
     * This method initializes the match
     * */
    fun init(){
        questions = Questions.loadMainQuestions()
        totalQuestions = Questions.loadAllQuestions().size
        results = loadResults()
        loadQuestion()
        currentAnswer = false
        finalResult = ResultModel(Animals.none, "")
        isMatchRunning = true
    }

    /**
     * This method loads the possible results of Player Match' mode
     * @return a map of results
     * */
    private fun loadResults() : MutableMap<String, ResultModel>{

        return mutableMapOf(
                "LION"    to ResultModel(Animals.lion, applicationContext.resources.getString(R.string.answer_lion)),
                "HORSE"    to ResultModel(Animals.horse, applicationContext.resources.getString(R.string.answer_horse)),
                "OSTRICH"    to ResultModel(Animals.ostrich, applicationContext.resources.getString(R.string.answer_ostrich)),
                "PENGUIN"    to ResultModel(Animals.penguin, applicationContext.resources.getString(R.string.answer_penguin)),
                "DUCK"    to ResultModel(Animals.duck, applicationContext.resources.getString(R.string.answer_duck)),
                "TURTLE"    to ResultModel(Animals.turtle, applicationContext.resources.getString(R.string.answer_turtle)),
                "CROCODILE"    to ResultModel(Animals.crocodile, applicationContext.resources.getString(R.string.answer_crocodile)),
                "WHALE"    to ResultModel(Animals.whale, applicationContext.resources.getString(R.string.answer_whale)),
                "HUMAN"    to ResultModel(Animals.human, applicationContext.resources.getString(R.string.answer_human)),
                "BAT"    to ResultModel(Animals.bat, applicationContext.resources.getString(R.string.answer_bat)),
                "MONKEY"    to ResultModel(Animals.monkey, applicationContext.resources.getString(R.string.answer_monkey)),
                "SNAKE"    to ResultModel(Animals.snake, applicationContext.resources.getString(R.string.answer_snake)),
                "EAGLE"    to ResultModel(Animals.eagle, applicationContext.resources.getString(R.string.answer_eagle))
        )
    }
    
    /**
     * This method tries to figure out which animal was chosen
     * @return true if it found a correct result
     * */
    fun checkResult(answer: Boolean) : Boolean{
        return compareResult(currentQuestion.title, answer)
    }

    private fun compareResult(characteristic : String, answer: Boolean) : Boolean{
        val newResults = results.filter {
            when(characteristic){
                "mammal" -> it.value.animal.isMammal == answer
                "quadruped" -> it.value.animal.isQuadruped == answer
                "carnivore" -> it.value.animal.isCarnivore == answer
                "herbivore" -> it.value.animal.isHerbivore == answer
                "flying" -> it.value.animal.isFlying == answer
                "fins" -> it.value.animal.hasFins == answer
                else -> return false
            }
        }

        results = newResults.toMutableMap()

        return if (newResults.size != 1){
            false
        }else{
            newResults.forEach {
                finalResult = it.value

            }
            true
        }
    }

    /**
     * This method finishes the current match
     * @return result's message
     * */
    fun finish() : String{
        if (finalResult.animal.breed == "none"){ finalResult = ResultModel(Animals.none,
                applicationContext.resources.getString(R.string.answer_none)) }
        val finalText = finalResult.resultText
        questions.clear()
        totalQuestions = 0
        results.clear()
        isMatchRunning = false
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
        val questMap = getRandomly()
        currentQuestion.title = questMap.value.title
        currentQuestion.text = questMap.value.text

        questions.remove(questMap.key) // remove item to not repeat the question again
        totalQuestions -= 1
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

    private fun getRandomly(): MutableMap.MutableEntry <String, QuestionModel> {
        val random = Random()
        return questions.entries.elementAt(random.nextInt(questions.size))
    }

    /**
     * This method gets the status of the match
     * @return true if the match still running
     */
    fun getIsMatchRunning() : Boolean{
        return isMatchRunning
    }

    fun getQuantityOfQuestions(): Int {
        return totalQuestions
    }
}