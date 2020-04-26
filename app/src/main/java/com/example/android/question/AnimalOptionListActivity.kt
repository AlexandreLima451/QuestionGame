package com.example.android.question

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.question.model.*
import com.example.android.question.model.adapter.AnimalItemAdapter
import com.example.android.question.model.adapter.AnimalItemClick
import kotlinx.android.synthetic.main.activity_animal_list.listview_animal
import kotlinx.android.synthetic.main.activity_animal_option_list.*

class AnimalOptionListActivity : AppCompatActivity() {

    val animalMutableList = mutableListOf<AnimalItem>()
    private var animalAdapter : AnimalItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_option_list)

        fun unselectAllAnimals(){
            animalMutableList.forEach{
                it.isSelected = false
            }
        }

        val animalUnselected = false

        animalMutableList.add(AnimalItem(R.drawable.img_lion, R.string.animal_lion, R.string.lion_description,
                Animals.lion, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_horse, R.string.animal_horse, R.string.horse_description,
                Animals.horse, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_penguin, R.string.animal_penguin, R.string.penguin_description,
                Animals.penguin, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_duck, R.string.animal_duck, R.string.duck_description,
                Animals.duck, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_turtle, R.string.animal_turtle, R.string.turtle_description,
                Animals.turtle, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_crocodile, R.string.animal_crocodile, R.string.crocodile_description,
                Animals.crocodile, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_whale, R.string.animal_whale, R.string.whale_description,
                Animals.whale, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.happy, R.string.animal_human, R.string.human_description,
                Animals.human, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_bat, R.string.animal_bat, R.string.bat_description,
                Animals.bat, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_monkey, R.string.animal_monkey, R.string.monkey_description,
                Animals.monkey, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_snake, R.string.animal_snake, R.string.snake_description,
                Animals.snake, animalUnselected))
        animalMutableList.add(AnimalItem(R.drawable.img_eagle, R.string.animal_eagle, R.string.eagle_description,
                Animals.eagle, animalUnselected))

        animalAdapter = AnimalItemAdapter(animalMutableList, this, object : AnimalItemClick {
            override fun onClick(itemView: View, content: AnimalItem, position: Int) {
                if(content.isSelected){
                    content.isSelected = false
                    animalAdapter?.notifyDataSetChanged()
                }else{
                    unselectAllAnimals()
                    content.isSelected = true
                    animalAdapter?.notifyDataSetChanged()
                }
            }
        })

        listview_animal.adapter = animalAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listview_animal.layoutManager = layoutManager

        btn_finish.setOnClickListener{
            var animalChosen = Animals.none
            animalMutableList.forEach{
                if (it.isSelected) animalChosen = it.animalObject!!
            }

            if (!animalChosen.equals(Animals.none)){
                val answerText = getQuestionMessage(animalChosen)

                val result = ResultModel(animalChosen, answerText)
                returnMatchWithFinalResult(result)
            }
        }

        btn_back.setOnClickListener{
            returnMatch()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }

    /**
     * This method returns to match with the answer chosen by the player
     */
    private fun returnMatchWithFinalResult(result : ResultModel){
        val intent = Intent(this, MachineMatchActivity::class.java)
        intent.putExtra("FINAL_RESULT", result)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    /**
     * This method returns to match
     */
    private fun returnMatch(){
        val intent = Intent(this, MachineMatchActivity::class.java)
        intent.putExtra("FINAL_RESULT", Results.noResult)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    /**
     * This method gets a answer according to animal was chosen
     * @param animal chosen
     * @return answer's text
     */
    private fun getQuestionMessage(animal : Animal) : String{
        when(animal.breed){
            "lion" -> return applicationContext.getString(R.string.player_answer_lion)
            "horse" -> return applicationContext.getString(R.string.player_answer_horse)
            "ostrich" -> return applicationContext.getString(R.string.player_answer_ostrich)
            "penguin" -> return applicationContext.getString(R.string.player_answer_penguin)
            "duck" -> return applicationContext.getString(R.string.player_answer_duck)
            "turtle" -> return applicationContext.getString(R.string.player_answer_turtle)
            "crocodile" -> return applicationContext.getString(R.string.player_answer_crocodile)
            "whale" -> return applicationContext.getString(R.string.player_answer_whale)
            "human" -> return applicationContext.getString(R.string.player_answer_human)
            "bat" -> return applicationContext.getString(R.string.player_answer_bat)
            "monkey" -> return applicationContext.getString(R.string.player_answer_monkey)
            "snake" -> return applicationContext.getString(R.string.player_answer_snake)
            "eagle" -> return applicationContext.getString(R.string.player_answer_eagle)
        }

        return ""
    }
}
