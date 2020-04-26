package com.example.android.question.model

import android.content.Context
import com.example.android.question.model.domain.Animals
import com.example.android.question.model.domain.Coin

/**
 * This class handles with the versus match's logic
 */
class VersusMatch (context : Context){

    private val machineMatch = MachineMatch(context)
    private val playerMatch = PlayerMatch(context)
    private var machineScore = 0
    private var playerScore = 0

    /**
     * This method initializes the versus match
     */
    fun init(){
        playerScore = 0
        machineScore = 0
        sortTurn()
    }

    /**
     * This method initializes the machine match
     * */
    private fun newMachineTurn(){
        if(playerMatch.getIsMatchRunning())
            playerMatch.finish()
        machineMatch.init()
    }

    /**
     * This method initializes the player match
     * */
    private fun newPlayerTurn(){
        if(machineMatch.getIsMatchRunning())
            machineMatch.finish(ResultModel(Animals.none, ""))
        playerMatch.init()
    }

    /**
     * This method sorts which kind of match will initialize
     * */
    fun sortTurn(){
        val isPlayerTurn = (Coin.flip() == Coin.HEAD)
        if(isPlayerTurn){
            newPlayerTurn()
        }else{
            newMachineTurn()
        }
    }

    /**
     * This method gives points to the computer
     * */
    private fun machineWin(points : Int){
        machineScore += points
    }

    /**
     * This method gives points to the player
     * */
    private fun playerWin(points : Int){
        playerScore += points
    }

    /**
     * This method counts the player's score and changes the turn
     * */
    fun finishMachineMatch(){
        val totalPoints = 8
        val totalQuantityOfQuestions = 6
        val discount = totalQuantityOfQuestions - machineMatch.getQuantityOfQuestions()
        val playerPoints = totalPoints - discount
        playerWin(playerPoints)
        newPlayerTurn()
    }

    /**
     * This method counts the computer's score and changes the turn
     * */
    fun finishPlayerMatch(){
        val totalPoints = 8
        val totalQuantityOfQuestions = 6
        val discount = totalQuantityOfQuestions - playerMatch.getQuantityOfQuestions()
        val machinePoints = totalPoints - discount
        machineWin(machinePoints)
        newMachineTurn()
    }

    /**
     * This method gets the player's score
     * */
    fun getPlayerScore() : Int {
        return playerScore
    }

    /**
     * This method gets the computer's score
     * */
    fun getMachineScore() : Int {
        return machineScore
    }

    /**
     * This method finishes the versus match and decides which player wins
     * */
    fun finish() : ResultModel{
        if(playerScore > machineScore){
            return ResultModel(Animals.none, "Você venceu, parabéns!")
        }else if(machineScore > playerScore){
            return ResultModel(Animals.none, "Eu ganhei, eu venci!")
        }else{
            return ResultModel(Animals.none, "Empate!!!")
        }
    }
}