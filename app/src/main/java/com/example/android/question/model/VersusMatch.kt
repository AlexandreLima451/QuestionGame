package com.example.android.question.model

import android.content.Context
import com.example.android.question.model.domain.Animals
import com.example.android.question.model.domain.Coin

/**
 * This class handles with the versus match's logic
 */
class VersusMatch private constructor(context : Context) {

    companion object{
        private val INSTANCE : VersusMatch? = null

        fun getInstance(context : Context) : VersusMatch{
            if(INSTANCE == null) return VersusMatch(context)
            else return INSTANCE
        }

        private var currentMatch : Match? = null
    }

    private var machineScore = 0
    private var playerScore = 0
    private val MAX_ROUNDS = 4 // four for each player...
    private var numberOfRound = 0

    private val applicationContext = context

    /**
     * This method initializes the versus match
     */
    fun init(){
        playerScore = 0
        machineScore = 0
        numberOfRound = MAX_ROUNDS * 2
        currentMatch = sortTurn()
    }

    /**
     * This method sorts which kind of match will initialize
     * @return a player or computer's match
     * */
    fun sortTurn() : Match{
        val isPlayerTurn = (Coin.flip() == Coin.HEAD)
        if(isPlayerTurn){
            return newPlayerTurn()
        }else{
            return newMachineTurn()
        }
    }

    /**
     * This method changes the player's turn
     * */
    private fun changeTurn() {
        if(isMachineMatch()){
            currentMatch = newPlayerTurn()
        }else{
            currentMatch = newMachineTurn()
        }
    }

    /**
     * This method goes to next round
     * */
    fun nextRound(){
        if(hasNextRound()){
            numberOfRound -= 1
            changeTurn()
        }
    }

    /**
     * This method checks if the match has a next round
     * @return true if it has a next round
     * */
    fun hasNextRound() : Boolean{
        return numberOfRound > 1
    }

    /**
     * This method initializes the machine match
     * @return a computer's match
     * */
    private fun newMachineTurn() : MachineMatch{
        if(currentMatch == null) currentMatch = MachineMatch.getInstance(applicationContext)
        if(currentMatch!!.isRunning())
            currentMatch!!.finish(ResultModel(Animals.none, ""))
        return MachineMatch.getInstance(applicationContext)
    }

    /**
     * This method initializes the player match
     * @return a player's match
     * */
    private fun newPlayerTurn() : PlayerMatch{
        if(currentMatch == null) currentMatch = PlayerMatch.getInstance(applicationContext)
        if(currentMatch!!.isRunning())
            currentMatch!!.finish(ResultModel(Animals.none, ""))
        return PlayerMatch.getInstance(applicationContext)
    }

    /**
     * This method gives points to the computer
     * @param points to increase the computer's score
     * */
    private fun machineWin(points : Int){
        machineScore += points
    }

    /**
     * This method gives points to the player
     * @param points to increase the player's score
     * */
    private fun playerWin(points : Int){
        playerScore += points
    }

    /**
     * This method counts the player's score
     * @param isPlayerWin is true if the player wins the match
     * */
    fun countPlayerPoints(isPlayerWin : Boolean){
        if(isPlayerWin){
            val machineMatch = currentMatch as MachineMatch
            val totalPoints = 8
            val totalQuantityOfQuestions = 6
            val discount = totalQuantityOfQuestions - machineMatch.getQuantityOfQuestions()
            val playerPoints = totalPoints - discount
            playerWin(playerPoints)
        }
    }

    /**
     * This method counts the computer's score
     * The computer doesn't make mistakes, so it always gets points here
     * @param isComputerWin is true if the computer wins the match
     * */
    fun countMachinePoints(isComputerWin : Boolean){
        val MINIMUM = 2
        if(isComputerWin){
            val playerMatch = currentMatch as PlayerMatch
            val totalPoints = 8
            val totalQuantityOfQuestions = 6
            val discount = totalQuantityOfQuestions - playerMatch.getQuantityOfQuestions()
            val machinePoints = totalPoints - discount
            machineWin(machinePoints)
        }else{
            machineWin(MINIMUM)
        }
    }

    /**
     * This method gets the player's score
     * @return the player's score
     * */
    fun getPlayerScore() : Int {
        return playerScore
    }

    /**
     * This method gets the computer's score
     * @return the computer's score
     * */
    fun getMachineScore() : Int {
        return machineScore
    }

    /**
     * This method finishes the versus match and decides which player wins
     * @return the final result of Versus Mode
     * */
    fun finish() : ResultModel{
        currentMatch = null
        val finalResult: ResultModel?
        if(playerScore > machineScore){
            finalResult = ResultModel(Animals.none, "Você venceu, parabéns!")
        }else if(machineScore > playerScore){
            finalResult = ResultModel(Animals.none, "Eu ganhei, eu venci!")
        }else{
            finalResult = ResultModel(Animals.none, "Empate!!!")
        }
        playerScore = 0
        machineScore = 0

        return finalResult
    }

    /**
     * This method gets the match as player's turn
     * @return the player's match
     * */
    fun convertCurrentMatchToPlayer() : PlayerMatch?{
        return currentMatch as PlayerMatch
    }

    /**
     * This method gets the match as computer's turn
     * @return the computer's match
     * */
    fun convertCurrentMatchToComputer() : MachineMatch{
        return currentMatch as MachineMatch
    }

    /**
     * This method gets the match as player's turn
     * @return true if the current match is a player's match
     * */
    fun isPlayerMatch() : Boolean{
        return currentMatch is PlayerMatch
    }

    /**
     * This method gets the match as computer's turn
     * @return true if the current match is a computer's match
     * */
    fun isMachineMatch() : Boolean{
        return currentMatch is MachineMatch
    }
}