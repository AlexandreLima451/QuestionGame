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
    private val VERSUS_MODE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        btn_players_turn_mode.setOnClickListener {
            val introPlayerGuessing = Introduction.newInstance()
            introPlayerGuessing.createMessage(getString(R.string.intro_player_guessing_message_1))
            introPlayerGuessing.createMessage(getString(R.string.intro_player_guessing_message_2))
            startPlayerTurnMode(applicationContext, introPlayerGuessing)
        }

        btn_machine_turn_mode.setOnClickListener {
            val introMachineGuessing = Introduction.newInstance()
            introMachineGuessing.createMessage(getString(R.string.intro_machine_guessing_message_1))
            introMachineGuessing.createMessage(getString(R.string.intro_machine_guessing_message_2))
            startMachineTurnMode(applicationContext, introMachineGuessing)
        }

        btn_versus_mode.setOnClickListener {
            val introVersusMode = Introduction.newInstance()
            introVersusMode.createMessage(getString(R.string.intro_versus_mode_message1))
            introVersusMode.createMessage(getString(R.string.intro_versus_mode_message2))
            introVersusMode.createMessage(getString(R.string.intro_versus_mode_message3))
            introVersusMode.createMessage(getString(R.string.intro_versus_mode_message4))
            introVersusMode.createMessage(getString(R.string.intro_versus_mode_message5))
            startVersusMode(applicationContext, introVersusMode)
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

    private fun startVersusMode(context : Context, introVersus : Introduction){
        val intent = Intent(context, ActivityGameIntroPart1::class.java )
        intent.putExtra("INTRO_MESSAGES", introVersus)
        intent.putExtra("MATCH_TYPE", this.VERSUS_MODE)
        startActivity(intent)
        finish()
    }
}
