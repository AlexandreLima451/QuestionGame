package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.question.model.Introduction
import kotlinx.android.synthetic.main.activity_game_intro_part1.*

/**
 * This class refers to the intro of the game (part 1)
 */
class ActivityGameIntroPart1 : AppCompatActivity() {

    private var introPlayerGuessing : Introduction = Introduction()

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

        introPlayerGuessing.createMessage(this.getString(R.string.intro_text_1))
        introPlayerGuessing.createMessage(this.getString(R.string.intro_text_2))
        introPlayerGuessing.createMessage(this.getString(R.string.intro_text_3))
        btn_start_quiz.visibility = View.INVISIBLE
        btn_next.visibility = View.VISIBLE

        intro_text.text = introPlayerGuessing.showMessage()
    }

    /**
     * This method loads the messages according the order below
     */
    private fun loadMessage() {
        introPlayerGuessing.loadNextMessage()
    }

    /**
     * This method loads the previous message
     */
    fun previous(){
        intro_text.text = introPlayerGuessing.loadPreviousMessage()
    }

    /**
     * This method loads the next message
     */
    fun next(){
        if(introPlayerGuessing.hasNext()){
            intro_text.text = introPlayerGuessing.loadNextMessage()
        }else{
            showAnimalList()
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
