package com.example.android.question

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_questions.*
import com.example.android.question.model.Match;

/**
 * This class handles with the game's logic
 */

class ActivityGameQuestions : AppCompatActivity() {

    var match = Match()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_questions)

        match.init()
        showQuestion()
    }

    fun showQuestion(){
        txt_dialog.text = match.currentQuestion.text
    }

    /**
     * This method sets the answer "yes" and loads a new question
     */
    fun clickYes(view : View){
        match.setAnswer(true)
        if(match.checkResult()){
            finishGame()
        }else{
            if(match.loadQuestion()){
                showQuestion()
            }else{
                finishGame()
            }
        }
    }

    /**
     * This method sets the answer "no" and loads a new question
     */
    fun clickNo(view : View){
        match.setAnswer(false)
        if(match.checkResult()){
            finishGame()
        }else{
            if(match.loadQuestion()){
                showQuestion()
            }else{
                finishGame()
            }
        }
    }

    /**
     * This method finishes the game
     */
    private fun finishGame(){
        txt_dialog.text = match.finish()

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

        match.reset()
        showQuestion()
    }
}
