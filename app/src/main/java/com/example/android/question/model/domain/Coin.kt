package com.example.android.question.model.domain

import kotlin.random.Random

/**
 * This class handles with a head or tails logic
 */
abstract class Coin {

    companion object{
        val HEAD = 0
        val TAIL = 1

        /**
         * This method flips the virtual coin and returns which side of coin was chosen
         */
        fun flip() : Int{
            do {
                val coinSide = Random.nextInt((TAIL - HEAD) + 1) + HEAD
                if(coinSide == HEAD) return HEAD
                if(coinSide == TAIL) return TAIL
            }while (coinSide == HEAD || coinSide == TAIL)

            return HEAD
        }
    }
}