/***
 * Question Game!
 *
 * App developed by
 * @author Alexandre Lima
 *
 * Originally developed as PC game for an exam that I did some years ago, here the machine challenges
 * the player to guess which animal he or she thought. I decided to remake this game here to put in
 * practice the lessons that I learned about Android App Development. I hope you enjoy it!
 *
 * 2012-2019
 */

package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This method starts the game
     * */
    fun startGame(view : View){
        var intent = Intent(applicationContext, ActivityGameIntro::class.java)
        startActivity(intent)
    }
}