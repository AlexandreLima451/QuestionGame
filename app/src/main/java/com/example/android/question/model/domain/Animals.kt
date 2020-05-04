package com.example.android.question.model.domain

import java.util.*

abstract class Animals {

    companion object{
        val lion = Animal(breed = "lion", isMammal = true, isQuadruped = true, isCarnivore = true,
                isHerbivore = false, isFlying = false, hasFins = false)
        val horse = Animal(breed = "horse", isMammal = true, isQuadruped = true, isCarnivore = false,
                isHerbivore = true, isFlying = false, hasFins = false)
        /*val ostrich = Animal(breed = "ostrich", isMammal = false, isQuadruped = false, isCarnivore = true,
                isHerbivore = true, isFlying = false, hasFins = false)*/
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
        val eagle = Animal(breed = "eagle", isMammal = false, isQuadruped = false, isCarnivore = true,
                isHerbivore = false, isFlying = true, hasFins = false)
        val none = Animal(breed = "none", isMammal = false, isQuadruped = false, isCarnivore = false,
                isHerbivore = false, isFlying = false, hasFins = false)

        fun listOfAnimals() : Map<Int, Animal>{
            val animals = mutableMapOf<Int, Animal>()
            animals[0] = lion
            animals[1] = horse
            animals[3] = penguin
            animals[4] = duck
            animals[5] = turtle
            animals[6] = crocodile
            animals[7] = whale
            animals[8] = human
            animals[9] = bat
            animals[10] = monkey
            animals[12] = snake
            animals[13] = eagle
            //animals[13] = ostrich

            return animals
        }

        fun randomAnimal() : Animal {
            val noAnimal = none
            val possibleAnimals = listOfAnimals()

            val size = possibleAnimals.size
            val item = Random().nextInt(size)

            possibleAnimals.forEach {
                if(it.key == item) return it.value
            }
            return noAnimal
        }
    }
}