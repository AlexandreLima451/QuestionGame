package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.question.model.AnimalItem
import com.example.android.question.model.Introduction
import com.example.android.question.model.adapter.AnimalItemAdapter
import com.example.android.question.model.adapter.AnimalItemClick
import kotlinx.android.synthetic.main.activity_game_intro_part2.*

/**
 * This class refers to the intro of the game (part 2)
 */
class ActivityGameIntroPart2 : AppCompatActivity() {

    private var introduction : Introduction = Introduction.newInstance()
    private var matchType : Int = 0
    private val PLAYER_GUESSING = 1
    private val MACHINE_GUESSING = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_intro_part2)

        val animalMutableList = mutableListOf<AnimalItem>()
        val animalUnselected = false

        animalMutableList.add(AnimalItem(R.drawable.img_lion, R.string.animal_lion, R.string.lion_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_horse, R.string.animal_horse, R.string.horse_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_penguin, R.string.animal_penguin, R.string.penguin_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_duck, R.string.animal_duck, R.string.duck_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_turtle, R.string.animal_turtle, R.string.turtle_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_crocodile, R.string.animal_crocodile, R.string.crocodile_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_whale, R.string.animal_whale, R.string.whale_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.happy, R.string.animal_human, R.string.human_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_bat, R.string.animal_bat, R.string.bat_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_monkey, R.string.animal_monkey, R.string.monkey_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_snake, R.string.animal_snake, R.string.snake_description, null, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_eagle, R.string.animal_eagle, R.string.eagle_description, null, animalUnselected))

        val animalAdapter = AnimalItemAdapter(animalMutableList, this, object : AnimalItemClick{
            override fun onClick(itemView: View, content: AnimalItem, position: Int) {
                return
            }
        })

        listview_animal.adapter = animalAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listview_animal.layoutManager = layoutManager

        introduction = intent.getSerializableExtra("INTRO_MESSAGES") as Introduction
        matchType = intent.getIntExtra("MATCH_TYPE", 0)

        btn_start_quiz.setOnClickListener {
            if (matchType == this.PLAYER_GUESSING) startPlayerMatch()
            if (matchType == this.MACHINE_GUESSING) startMachineMatch()
        }

        btn_previous.setOnClickListener {
            previous()
        }
    }

    /**
     * This method starts a new match (Player Guessing)
     */
    private fun startPlayerMatch(){
        val intent = Intent(this, PlayerMatchActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * This method starts a new match (Machine Guessing)
     */
    private fun startMachineMatch(){
        val intent = Intent(this, MachineMatchActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * This method goes to beginning of introduction
     */
    private fun previous(){
        val intent = Intent(this, ActivityGameIntroPart1::class.java)
        intent.putExtra("INTRO_MESSAGES", introduction)
        intent.putExtra("MATCH_TYPE", matchType)
        startActivity(intent)
        finish()
    }
}
