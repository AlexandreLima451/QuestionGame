package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_intro.*

/**
 * This class refers to the intro of the game
 */
class ActivityGameIntro : AppCompatActivity() {

    var currentMessage : String = ""
    var currentMsgCode : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_intro)

        /**
         * set the configuration of the introduction
         */
        btn_start_quiz.visibility = View.INVISIBLE;
        btn_next.visibility = View.VISIBLE;
        currentMsgCode = 1
        loadMessage()
        intro_text.text = currentMessage
    }

    /**
     * This method loads the messages according the order below
     */
    fun loadMessage() {
        when (currentMsgCode) {
            1 -> currentMessage = resources.getString(R.string.intro_text_1)
            2 -> currentMessage = resources.getString(R.string.intro_text_2)
            3 -> currentMessage = resources.getString(R.string.intro_text_3)
            4 -> currentMessage = resources.getString(R.string.intro_text_4)
            5 -> currentMessage = resources.getString(R.string.intro_text_5)
            else -> { // Note the block
                currentMessage = " ? "
            }
        }

        if(currentMsgCode == 5){
            btn_start_quiz.visibility = View.VISIBLE;
            btn_next.visibility = View.INVISIBLE;
        }else{
            btn_start_quiz.visibility = View.INVISIBLE;
            btn_next.visibility = View.VISIBLE;
        }
    }

    /**
     * This method loads the previous message
     */
    fun previous(view : View){
        if (currentMsgCode > 1) currentMsgCode -= 1  // the lower value is 1
        loadMessage()
        intro_text.text = currentMessage
    }

    /**
     * This method loads the next message
     */
    fun next(view : View){
        currentMsgCode += 1
        loadMessage()
        intro_text.text = currentMessage
    }

    /**
     * This method starts a new match
     */
    fun startQuestions(view : View){
        var intent = Intent(this, ActivityGameQuestions::class.java)
        startActivity(intent)
    }
}
