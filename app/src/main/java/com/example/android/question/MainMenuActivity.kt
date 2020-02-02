package com.example.android.question

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.question.model.Introduction
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        btn_players_turn_mode.setOnClickListener {
            val introPlayerGuessing = Introduction.newInstance()
            introPlayerGuessing.createMessage(this.getString(R.string.intro_text_1))
            introPlayerGuessing.createMessage(this.getString(R.string.intro_text_2))
            introPlayerGuessing.createMessage(this.getString(R.string.intro_text_3))
            startPlayerTurnMode(applicationContext, introPlayerGuessing)
        }

        btn_machine_turn_mode.setOnClickListener {

        }

        btn_versus_mode.setOnClickListener {

        }
    }

    private fun startPlayerTurnMode(context : Context, introPlayerGuessing : Introduction){
        val intent = Intent(context, ActivityGameIntroPart1::class.java )
        intent.putExtra("INTRO_MESSAGES", introPlayerGuessing)
        startActivity(intent)
        finish()
    }
}
