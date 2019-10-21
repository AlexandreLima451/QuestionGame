package com.example.android.question.model

class Animal (var breed : String?,
              var isMammal : Boolean,
              var isQuadruped : Boolean,
              var isCarnivore : Boolean,
              var isHerbivore : Boolean,
              var isFlying : Boolean,
              var hasFins : Boolean){

    var imgPixelArt : String? = null

    fun equals(anotherAnimal : Animal) : Boolean{
        if(this.isMammal != anotherAnimal.isMammal){ return false }
        if(this.isQuadruped != anotherAnimal.isQuadruped){ return false }
        if(this.isCarnivore != anotherAnimal.isCarnivore){ return false }
        if(this.isHerbivore != anotherAnimal.isHerbivore){ return false }
        if(this.isFlying != anotherAnimal.isFlying){ return false }
        if(this.hasFins != anotherAnimal.hasFins){ return false }

        return true
    }
}
