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

    private var introduction : Introduction = Introduction.newInstance()
    private var matchType : Int = 0

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
        introduction = intent.getSerializableExtra("INTRO_MESSAGES") as Introduction
        matchType = intent.getIntExtra("MATCH_TYPE", 1)
        btn_start_quiz.visibility = View.INVISIBLE
        btn_next.visibility = View.VISIBLE

        intro_text.text = introduction.showMessage()
    }

    /**
     * This method loads the previous message
     */
    fun previous(){
        if(introduction.hasPrevious())
            intro_text.text = introduction.loadPreviousMessage()
        else showMainMenu()
    }

    /**
     * This method loads the next message
     */
    fun next(){
        if(introduction.hasNext())
            intro_text.text = introduction.loadNextMessage()
        else showAnimalList()
    }

    /**
     * This method shows the animal list
     */
    private fun showAnimalList(){
        val intent = Intent(this, ActivityGameIntroPart2::class.java)
        intent.putExtra("INTRO_MESSAGES", introduction)
        intent.putExtra("MATCH_TYPE", matchType)
        startActivity(intent)
        finish()
    }

    /**
     * This method returns to main menu
     */
    private fun showMainMenu(){
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}