package com.example.android.question

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.question.model.Introduction
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    private val PLAYER_GUESSING = 1
    private val MACHINE_GUESSING = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        btn_players_turn_mode.setOnClickListener {
            val introPlayerGuessing = Introduction.newInstance()
            introPlayerGuessing.createMessage(this.getString(R.string.intro_player_guessing_message_1))
            introPlayerGuessing.createMessage(this.getString(R.string.intro_player_guessing_message_2))
            introPlayerGuessing.createMessage(this.getString(R.string.intro_player_guessing_message_3))
            startPlayerTurnMode(applicationContext, introPlayerGuessing)
        }

        btn_machine_turn_mode.setOnClickListener {
            val introMachineGuessing = Introduction.newInstance()
            introMachineGuessing.createMessage(this.getString(R.string.intro_machine_guessing_message_1))
            introMachineGuessing.createMessage(this.getString(R.string.intro_machine_guessing_message_2))
            introMachineGuessing.createMessage(this.getString(R.string.intro_machine_guessing_message_3))
            startMachineTurnMode(applicationContext, introMachineGuessing)
        }

    }

    private fun startPlayerTurnMode(context : Context, introPlayerGuessing : Introduction){
        val intent = Intent(context, ActivityGameIntroPart1::class.java )
        intent.putExtra("INTRO_MESSAGES", introPlayerGuessing)
        intent.putExtra("MATCH_TYPE", this.PLAYER_GUESSING)
        startActivity(intent)
        finish()
    }

    private fun startMachineTurnMode(context : Context, introMachineGuessing : Introduction){
        val intent = Intent(context, ActivityGameIntroPart1::class.java )
        intent.putExtra("INTRO_MESSAGES", introMachineGuessing)
        intent.putExtra("MATCH_TYPE", this.MACHINE_GUESSING)
        startActivity(intent)
        finish()
    }
}
