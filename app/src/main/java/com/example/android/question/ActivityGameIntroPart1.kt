package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_intro_part1.*

/**
 * This class refers to the intro of the game (part 1)
 */
class ActivityGameIntroPart1 : AppCompatActivity() {

    private var currentMessage : String = ""
    private var currentMsgCode : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_intro_part1)

        btn_next.setOnClickListener {
            next()
        }

        btn_previous.setOnClickListener {
            previous()
        }

        btn_start_quiz.setOnClickListener {
            showAnimalList()
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
            else -> { // Note the block
                " ? "
            }
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
        if(currentMsgCode == 3){
           showAnimalList()
        }else{
            currentMsgCode += 1
            loadMessage()
            intro_text.text = currentMessage
        }
    }

    /**
     * This method shows the animal list
     */
    private fun showAnimalList(){
        val intent = Intent(this, ActivityGameIntroPart2::class.java)
        startActivity(intent)
        finish()
    }
}
