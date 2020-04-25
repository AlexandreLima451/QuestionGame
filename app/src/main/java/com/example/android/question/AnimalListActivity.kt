package com.example.android.question

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.question.model.AnimalItem
import com.example.android.question.model.adapter.AnimalItemAdapter
import com.example.android.question.model.adapter.AnimalItemClick
import kotlinx.android.synthetic.main.activity_animal_list.*

class AnimalListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)

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

        val animalAdapter = AnimalItemAdapter(animalMutableList, this, object : AnimalItemClick {
            override fun onClick(itemView: View, content: AnimalItem, position: Int) {
                return
            }
        })

        listview_animal.adapter = animalAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listview_animal.layoutManager = layoutManager
    }
}
