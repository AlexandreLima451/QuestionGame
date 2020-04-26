package com.example.android.question.model.list.item

import com.example.android.question.model.domain.Animal

data class AnimalItem(val animalImage: Int,
                      val animalBreed: Int,
                      val animalDescription : Int,
                      val animalObject : Animal?,
                      var isSelected : Boolean)