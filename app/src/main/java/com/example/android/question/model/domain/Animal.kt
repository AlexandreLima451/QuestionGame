package com.example.android.question.model.domain

import java.io.Serializable

open class Animal (var breed : String?,
                   var isMammal : Boolean,
                   var isQuadruped : Boolean,
                   var isCarnivore : Boolean,
                   var isHerbivore : Boolean,
                   var isFlying : Boolean,
                   var hasFins : Boolean) : Serializable{

    override fun equals(other: Any?): Boolean {
        val anotherAnimal = other as Animal

        if(this.isMammal != anotherAnimal.isMammal){ return false }
        if(this.isQuadruped != anotherAnimal.isQuadruped){ return false }
        if(this.isCarnivore != anotherAnimal.isCarnivore){ return false }
        if(this.isHerbivore != anotherAnimal.isHerbivore){ return false }
        if(this.isFlying != anotherAnimal.isFlying){ return false }
        if(this.hasFins != anotherAnimal.hasFins){ return false }

        return true
    }

    override fun hashCode(): Int {
        var result = breed?.hashCode() ?: 0
        result = 31 * result + isMammal.hashCode()
        result = 31 * result + isQuadruped.hashCode()
        result = 31 * result + isCarnivore.hashCode()
        result = 31 * result + isHerbivore.hashCode()
        result = 31 * result + isFlying.hashCode()
        result = 31 * result + hasFins.hashCode()
        return result
    }
}
