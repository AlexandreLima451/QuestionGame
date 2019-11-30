package com.example.android.question

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.question.model.AnimalItem
import com.example.android.question.model.AnimalItemAdapter
import kotlinx.android.synthetic.main.activity_animal_list.*

class AnimalListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)

        val animalMutableList = mutableListOf<AnimalItem>()

        animalMutableList.add(AnimalItem(R.drawable.img_lion, R.string.animal_lion))
        animalMutableList.add(AnimalItem(R.drawable.img_horse, R.string.animal_horse))
        animalMutableList.add(AnimalItem(R.drawable.img_penguin, R.string.animal_penguin))
        animalMutableList.add(AnimalItem(R.drawable.img_duck, R.string.animal_duck))
        animalMutableList.add(AnimalItem(R.drawable.img_turtle, R.string.animal_turtle))
        animalMutableList.add(AnimalItem(R.drawable.img_crocodile, R.string.animal_crocodile))
        animalMutableList.add(AnimalItem(R.drawable.img_whale, R.string.animal_whale))
        animalMutableList.add(AnimalItem(R.drawable.happy, R.string.animal_human))
        animalMutableList.add(AnimalItem(R.drawable.img_bat, R.string.animal_bat))
        animalMutableList.add(AnimalItem(R.drawable.img_monkey, R.string.animal_monkey))
        animalMutableList.add(AnimalItem(R.drawable.img_snake, R.string.animal_snake))
        animalMutableList.add(AnimalItem(R.drawable.img_eagle, R.string.animal_eagle))

        val animalAdapter = AnimalItemAdapter(this, animalMutableList)

        listview_animal.adapter = animalAdapter
    }
}
