package com.example.android.question.model

import android.content.res.Resources
import com.example.android.question.R
import com.example.android.question.util.MainApplication

abstract class Results {

    companion object {

        val lion = Animal(breed = "lion", isMammal = true, isQuadruped = true, isCarnivore = true,
                          isHerbivore = false, isFlying = false, hasFins = false)
        val horse = Animal(breed = "horse", isMammal = true, isQuadruped = true, isCarnivore = false,
                isHerbivore = true, isFlying = false, hasFins = false)
        val ostrich = Animal(breed = "ostrich", isMammal = false, isQuadruped = false, isCarnivore = true,
                isHerbivore = true, isFlying = false, hasFins = false)
        val penguin = Animal(breed = "penguin", isMammal = false, isQuadruped = false, isCarnivore = true,
                isHerbivore = false, isFlying = false, hasFins = true)
        val duck = Animal(breed = "duck", isMammal = false, isQuadruped = false, isCarnivore = false,
                isHerbivore = true, isFlying = true, hasFins = false)
        val turtle = Animal(breed = "turtle", isMammal = false, isQuadruped = true, isCarnivore = true,
                isHerbivore = true, isFlying = false, hasFins = true)
        val crocodile = Animal(breed = "crocodile", isMammal = false, isQuadruped = true, isCarnivore = true,
                isHerbivore = false, isFlying = false, hasFins = false)
        val whale = Animal(breed = "whale", isMammal = true, isQuadruped = false, isCarnivore = true,
                isHerbivore = true, isFlying = false, hasFins = true)
        val human = Animal(breed = "human", isMammal = true, isQuadruped = false, isCarnivore = true,
                isHerbivore = true, isFlying = false, hasFins = false)
        val bat = Animal(breed = "bat", isMammal = true, isQuadruped = true, isCarnivore = true,
                isHerbivore = true, isFlying = true, hasFins = false)
        val monkey = Animal(breed = "monkey", isMammal = true, isQuadruped = true, isCarnivore = true,
                isHerbivore = true, isFlying = false, hasFins = false)
        val snake = Animal(breed = "snake", isMammal = false, isQuadruped = false, isCarnivore = true,
                isHerbivore = false, isFlying = false, hasFins = false)
        val none = Animal(breed = "none", isMammal = false, isQuadruped = false, isCarnivore = false,
                isHerbivore = false, isFlying = false, hasFins = false)

        fun newInstance() : MutableMap<String, ResultModel>{
            val list = mutableMapOf<String, ResultModel>(
                    "LION"    to ResultModel(lion, MainApplication.applicationContext().resources.getString(R.string.answer_lion)),
                    "HORSE"    to ResultModel(horse, MainApplication.applicationContext().resources.getString(R.string.answer_horse)),
                    "OSTRICH"    to ResultModel(ostrich, MainApplication.applicationContext().resources.getString(R.string.answer_ostrich)),
                    "PENGUIN"    to ResultModel(penguin, MainApplication.applicationContext().resources.getString(R.string.answer_penguin)),
                    "DUCK"    to ResultModel(duck, MainApplication.applicationContext().resources.getString(R.string.answer_duck)),
                    "TURTLE"    to ResultModel(turtle, MainApplication.applicationContext().resources.getString(R.string.answer_turtle)),
                    "CROCODILE"    to ResultModel(crocodile, MainApplication.applicationContext().resources.getString(R.string.answer_crocodile)),
                    "WHALE"    to ResultModel(whale, MainApplication.applicationContext().resources.getString(R.string.answer_whale)),
                    "HUMAN"    to ResultModel(human, MainApplication.applicationContext().resources.getString(R.string.answer_human)),
                    "BAT"    to ResultModel(bat, MainApplication.applicationContext().resources.getString(R.string.answer_bat)),
                    "MONKEY"    to ResultModel(monkey, MainApplication.applicationContext().resources.getString(R.string.answer_monkey)),
                    "SNAKE"    to ResultModel(snake, MainApplication.applicationContext().resources.getString(R.string.answer_snake)),
                    "NONE"     to ResultModel(none, MainApplication.applicationContext().resources.getString(R.string.answer_none))
            )

            return list
        }
    }

}