package com.example.android.question

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.question.model.AnimalItem
import com.example.android.question.model.adapter.AnimalItemAdapter
import kotlinx.android.synthetic.main.activity_animal_list.*

class AnimalListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)

        val animalMutableList = mutableListOf<AnimalItem>()

        animalMutableList.add(AnimalItem(R.drawable.img_lion, R.string.animal_lion, R.string.lion_description))
        animalMutableList.add(AnimalItem(R.drawable.img_horse, R.string.animal_horse, R.string.horse_description))
        animalMutableList.add(AnimalItem(R.drawable.img_penguin, R.string.animal_penguin, R.string.penguin_description))
        animalMutableList.add(AnimalItem(R.drawable.img_duck, R.string.animal_duck, R.string.duck_description))
        animalMutableList.add(AnimalItem(R.drawable.img_turtle, R.string.animal_turtle, R.string.turtle_description))
        animalMutableList.add(AnimalItem(R.drawable.img_crocodile, R.string.animal_crocodile, R.string.crocodile_description))
        animalMutableList.add(AnimalItem(R.drawable.img_whale, R.string.animal_whale, R.string.whale_description))
        animalMutableList.add(AnimalItem(R.drawable.happy, R.string.animal_human, R.string.human_description))
        animalMutableList.add(AnimalItem(R.drawable.img_bat, R.string.animal_bat, R.string.bat_description))
        animalMutableList.add(AnimalItem(R.drawable.img_monkey, R.string.animal_monkey, R.string.monkey_description))
        animalMutableList.add(AnimalItem(R.drawable.img_snake, R.string.animal_snake, R.string.snake_description))
        animalMutableList.add(AnimalItem(R.drawable.img_eagle, R.string.animal_eagle, R.string.eagle_description))

        val animalAdapter = AnimalItemAdapter(this, animalMutableList)

        listview_animal.adapter = animalAdapter
    }
}
