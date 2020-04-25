package com.example.android.question.model

data class AnimalItem(val animalImage: Int,
                      val animalBreed: Int,
                      val animalDescription : Int,
                      val animalObject : Animal?,
                      var isSelected : Boolean)