package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        btn_players_turn_mode.setOnClickListener {

        }

        btn_machine_turn_mode.setOnClickListener {

        }

        btn_versus_mode.setOnClickListener {

        }
    }

    fun startPlayerTurnMode(){
        val intent = Intent(applicationContext, ActivityGameIntroPart1::class.java)
        startActivity(intent)
        finish()
    }

    fun startMachineTurnMode(){

    }

    fun startVersusMode(){

    }
}
