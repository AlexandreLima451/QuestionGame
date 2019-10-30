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

    private var currentMessage : String = ""
    private var currentMsgCode : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_intro)

        btn_next.setOnClickListener {
            next()
        }

        btn_previous.setOnClickListener {
            previous()
        }

        btn_start_quiz.setOnClickListener {
            startQuestions()
        }

        /**
         * setup the configuration of the introduction
         */
        btn_start_quiz.visibility = View.INVISIBLE
        btn_next.visibility = View.VISIBLE
        currentMsgCode = 1
        loadMessage()
        intro_text.text = currentMessage
    }

    /**
     * This method loads the messages according the order below
     */
    private fun loadMessage() {
        this.currentMessage = when (currentMsgCode) {
            1 -> resources.getString(R.string.intro_text_1)
            2 -> resources.getString(R.string.intro_text_2)
            3 -> resources.getString(R.string.intro_text_3)
            4 -> resources.getString(R.string.intro_text_4)
            5 -> resources.getString(R.string.intro_text_5)
            else -> { // Note the block
                " ? "
            }
        }

        if(currentMsgCode == 5){
            btn_start_quiz.visibility = View.VISIBLE
            btn_next.visibility = View.INVISIBLE
        }else{
            btn_start_quiz.visibility = View.INVISIBLE
            btn_next.visibility = View.VISIBLE
        }
    }

    /**
     * This method loads the previous message
     */
    fun previous(){
        if (currentMsgCode > 1) currentMsgCode -= 1  // the lower value is 1
        loadMessage()
        intro_text.text = currentMessage
    }

    /**
     * This method loads the next message
     */
    fun next(){
        currentMsgCode += 1
        loadMessage()
        intro_text.text = currentMessage
    }

    /**
     * This method starts a new match
     */
    private fun startQuestions(){
        val intent = Intent(this, ActivityGameQuestions::class.java)
        startActivity(intent)
    }
}
