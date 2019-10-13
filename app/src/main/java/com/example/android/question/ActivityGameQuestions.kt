package com.example.android.question

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_questions.*

/**
 * This class handles with the game's logic
 */

class ActivityGameQuestions : AppCompatActivity() {

    var questionText : String = ""
    var questionNumber : Int = 1
    var score : String = ""

    //KEY is result and VALUE is the answers, being Y equal yes and N equals no
    val mapResults = mapOf("LION" to "YYYN",
                           "HORSE" to "YYNY",
                           "OSTRICH" to "NNYY",
                           "PENGUIN" to "NNYN",
                           "DUCK" to "NNNY",
                           "TURTLE" to "NYYY",
                           "CROCODILE" to "NYYN",
                           "WHALE" to "YNYYY",
                           "HUMAN" to "YNYYN",
                           "BAT" to "YYYYY",
                           "MONKTEY" to "YYYYN" )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_questions)

        questionNumber = 0
        txt_dialog.text = loadQuestion()
    }

    /**
     * This method loads the questions
     */
    fun loadQuestion() : String {
        if(questionNumber == 4) {
            if (checkResult()) {
                finishGame()
                return showAnswer(score)
            } else {
                if (score == "YNYY") {
                    questionNumber += 1
                }else if (score == "YYYY") {
                    questionNumber += 2
                } else {
                    finishGame()
                    return showAnswer(score)
                }
            }
        } else if(questionNumber >= 5) {
          finishGame()
          return showAnswer(score)
        }else{
            questionNumber += 1
        }

        when (questionNumber) {
            1 -> return resources.getString(R.string.question_1)
            2 -> return resources.getString(R.string.question_2)
            3 -> return resources.getString(R.string.question_3)
            4 -> return resources.getString(R.string.question_4)
            5 -> return resources.getString(R.string.question_5)
            6 -> return resources.getString(R.string.question_6)
            else -> {
                return "null"
            }
        }
    }

    /**
     * This method sets the answer "yes" and loads a new question
     */
    fun clickYes(view : View){
        score += "Y"
        txt_dialog.text = loadQuestion()
    }

    /**
     * This method sets the answer "no" and loads a new question
     */
    fun clickNo(view : View){
        score += "N"
        txt_dialog.text = loadQuestion()
    }

    /**
     * This method validates the final answer
     */
    private fun checkResult() : Boolean{
        for ((value) in mapResults) {
            if(score == value) return true
        }
        return false
    }

    /**
     * This method shows the result according the answers
     * @param result of the match
     */
    private fun showAnswer(result : String) : String{
        var answerText : String = "";

        when(result){
            mapResults["LION"] -> answerText = resources.getString(R.string.answer_lion)
            mapResults["HORSE"] -> answerText = resources.getString(R.string.answer_horse)
            mapResults["OSTRICH"] -> answerText = resources.getString(R.string.answer_ostrich)
            mapResults["PENGUIN"] -> answerText = resources.getString(R.string.answer_penguin)
            mapResults["DUCK"] -> answerText = resources.getString(R.string.answer_duck)
            mapResults["TURTLE"] -> answerText = resources.getString(R.string.answer_turtle)
            mapResults["CROCODILE"] -> answerText = resources.getString(R.string.answer_crocodile)
            mapResults["WHALE"] -> answerText = resources.getString(R.string.answer_whale)
            mapResults["HUMAN"] -> answerText = resources.getString(R.string.answer_human)
            mapResults["BAT"] -> answerText = resources.getString(R.string.answer_bat)
            mapResults["MONKEY"] -> answerText = resources.getString(R.string.answer_monkey)
            else -> {
                answerText = resources.getString(R.string.answer_no_one)
            }
        }

        return answerText
    }

    /**
     * This method finishes the game
     */
    private fun finishGame(){
        var result : String = ""
        result = showAnswer(score)

        txt_dialog.text = result

        btn_yes.visibility = View.INVISIBLE
        btn_no.visibility = View.INVISIBLE
        btn_reset.visibility = View.VISIBLE
    }

    /**
     * This method initiates a new match
     */
    fun resetGame(view : View){
        btn_yes.visibility = View.VISIBLE
        btn_no.visibility = View.VISIBLE
        btn_reset.visibility = View.INVISIBLE

        questionText = ""
        questionNumber = 0
        score = ""
        txt_dialog.text = loadQuestion()
    }
}
