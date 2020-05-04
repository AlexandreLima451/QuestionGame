package com.example.android.question.model

import android.content.Context
import com.example.android.question.R
import com.example.android.question.model.domain.Animal
import com.example.android.question.model.domain.Animals
import com.example.android.question.model.domain.Results
import kotlin.random.Random

/**
 * This class handles with the machine match's logic
 */
class MachineMatch private constructor (context: Context) : Match{

    companion object{
        private val INSTANCE : MachineMatch? = null

        fun getInstance(context : Context) : MachineMatch{
            if(INSTANCE == null) return MachineMatch(context)
            else return INSTANCE
        }
    }

    private var questions : MutableList<QuestionModel> = mutableListOf()
    var chosenAnimal = Animals.none
    private val applicationContext = context
    private var isMatchRunning = false

    /**
     * This method initializes the match
     * */
    override fun init(){
        chosenAnimal = Animals.randomAnimal()
        questions = loadQuestions()
        isMatchRunning = true
    }

    /**
     * This method resets the game
     * */
    fun reset(){
        init()
    }

    /**
     * This method confirms if the animal chosen by the player is the correct animal
     * @param result is the player's answer
     * @return true if they are equal
     * */
    fun checkAnswer(result : ResultModel) : Boolean{
        return chosenAnimal.equals(result.animal)
    }

    /**
     * This method gets the machine's answer according the question asked by the player
     * @param animalBreed that player wants to know
     * @return machine's answer
     * */
    private fun getAnswerText(animalBreed: String): String {
        var answerText = "none"

        when(animalBreed){
            "mammal" -> answerText = applicationContext.getString(R.string.answer_animal_is_mammal)
            "quadruped" -> answerText = applicationContext.getString(R.string.answer_animal_is_quadruped)
            "carnivore" -> answerText = applicationContext.getString(R.string.answer_animal_is_carnivore)
            "herbivore" -> answerText = applicationContext.getString(R.string.answer_animal_is_herbivore)
            "flying" -> answerText = applicationContext.getString(R.string.answer_animal_is_flying)
            "fins" -> answerText = applicationContext.getString(R.string.answer_animal_has_fins)
            "none" -> answerText = applicationContext.getString(R.string.mach_negative_answer)
            "noQuestion" -> answerText = applicationContext.getString(R.string.no_questions_remain)
        }

        return answerText
    }

    /**
     * This method gets the answer for the question asked by the player
     * @return the answer
     */
    fun answerQuestion(question : QuestionModel) : String{
        var positiveAnswer = false

        chosenAnimal.let {
            when(question.title){
                "mammal" -> positiveAnswer = it.isMammal
                "quadruped" -> positiveAnswer = it.isQuadruped
                "carnivore" -> positiveAnswer = it.isCarnivore
                "herbivore" -> positiveAnswer = it.isHerbivore
                "flying" -> positiveAnswer = it.isFlying
                "fins" -> positiveAnswer = it.hasFins
            }
        }

        if (positiveAnswer) {
            return getAnswerText(question.title)
        }else{
            if (getQuantityOfQuestions() == 0) {
                return getAnswerText("noQuestion")
            }else{
                return getAnswerText("none")
            }
        }
    }

    /**
     * This method loads a list of questions for the player
     * @return a list of questions that player can ask
     */
    fun loadQuestions() : MutableList<QuestionModel>{
        val questions = mutableListOf<QuestionModel>()
        questions.add(QuestionModel("mammal", applicationContext.getString(R.string.opt_quest_mammal)))
        questions.add(QuestionModel("herbivore", applicationContext.getString(R.string.opt_quest_herbivore)))
        questions.add(QuestionModel("quadruped", applicationContext.getString(R.string.opt_quest_quadruped)))
        questions.add(QuestionModel("carnivore", applicationContext.getString(R.string.opt_quest_carnivore)))
        questions.add(QuestionModel("fins", applicationContext.getString(R.string.opt_quest_fins)))
        questions.add(QuestionModel("flying", applicationContext.getString(R.string.opt_quest_flying)))

        return questions
    }

    /**
     * This method gets the list of questions that remains for the player
     * @return a list of questions that player can ask
     */
    fun getQuestions(): MutableList<QuestionModel> {
        return questions
    }

    fun removeQuestion(question : QuestionModel){
        questions.remove(question)
    }

    fun getQuantityOfQuestions() : Int{
        return questions.size
    }

    /**
     * This method finishes the current match
     * @param result is the answer chosen by the player
     * @return result's message
     */
    override fun finish(result : ResultModel) : String {
        val finalText=  validateAnswer(result)
        questions.clear()
        isMatchRunning = false
        return finalText
    }

    /**
     * This method validates if the answer chosen by the player is correct or not.
     * @param result chosen by the player
     * @return result machine's message
     */
    private fun validateAnswer(result : ResultModel?) : String {
        var finalResult = Results.noResult
        if(result != null) finalResult = result

        if(chosenAnimal.equals(Animals.none)){
            return applicationContext.getString(R.string.match_neutral_answer)
        }

        if (!finalResult.animal.equals(Animals.none)){
            if(checkAnswer(finalResult)){
                return getPositiveResultText(chosenAnimal)
            }else{
                return getNegativeResultText(chosenAnimal)
            }
        }
        return ""
    }

    /**
     * This method gets a positive answer according the animal was chosen
     * @param animal chosen by the player
     * @return result machine's positive message
     */
    private fun getPositiveResultText(animal : Animal) : String {
        when(animal.breed){
            "lion" -> return applicationContext.getString(R.string.match_positive_lion)
            "horse" -> return applicationContext.getString(R.string.match_positive_horse)
            //"ostrich" -> return applicationContext.getString(R.string.match_positive_ostrich)
            "penguin" -> return applicationContext.getString(R.string.match_positive_penguin)
            "duck" -> return applicationContext.getString(R.string.match_positive_duck)
            "turtle" -> return applicationContext.getString(R.string.match_positive_turtle)
            "crocodile" -> return applicationContext.getString(R.string.match_positive_crocodile)
            "whale" -> return applicationContext.getString(R.string.match_positive_whale)
            "human" -> return applicationContext.getString(R.string.match_positive_human)
            "bat" -> return applicationContext.getString(R.string.match_positive_bat)
            "monkey" -> return applicationContext.getString(R.string.match_positive_monkey)
            "snake" -> return applicationContext.getString(R.string.match_positive_snake)
            "eagle" -> return applicationContext.getString(R.string.match_positive_eagle)
        }
        return applicationContext.getString(R.string.match_neutral_answer)
    }

    /**
     * This method gets a negative text according the animal was chosen
     * @param animal chosen by the player
     * @return result machine's negative text
     */
    private fun getNegativeResultText(animal : Animal) : String {
        when(animal.breed){
            "lion" -> return applicationContext.getString(R.string.match_negative_lion)
            "horse" -> return applicationContext.getString(R.string.match_negative_horse)
            //"ostrich" -> return applicationContext.getString(R.string.match_negative_ostrich)
            "penguin" -> return applicationContext.getString(R.string.match_negative_penguin)
            "duck" -> return applicationContext.getString(R.string.match_negative_duck)
            "turtle" -> return applicationContext.getString(R.string.match_negative_turtle)
            "crocodile" -> return applicationContext.getString(R.string.match_negative_crocodile)
            "whale" -> return applicationContext.getString(R.string.match_negative_whale)
            "human" -> return applicationContext.getString(R.string.match_negative_human)
            "bat" -> return applicationContext.getString(R.string.match_negative_bat)
            "monkey" -> return applicationContext.getString(R.string.match_negative_monkey)
            "snake" -> return applicationContext.getString(R.string.match_negative_snake)
            "eagle" -> return applicationContext.getString(R.string.match_negative_eagle)
        }
        return applicationContext.getString(R.string.match_neutral_answer)
    }

    /**
     * This method gets a random message for match's introduction
     * @return introduction's message
     */
    fun getMatchIntro() : String{
        val minValue = 1
        val maxValue = 3
        val introMachineOption = Random.nextInt((maxValue - minValue) + 1) + minValue
        var introMachineText = ""

        when(introMachineOption){
            1 -> introMachineText = applicationContext.getString(R.string.machine_match_intro1)
            2 -> introMachineText = applicationContext.getString(R.string.machine_match_intro2)
            3 -> introMachineText = applicationContext.getString(R.string.machine_match_intro3)
        }
        return introMachineText
    }

    /**
     * This method gets the status of the match
     * @return true if the match still running
     */
    private fun getIsMatchRunning() : Boolean{
        return isMatchRunning
    }

    override fun isRunning(): Boolean {
        return getIsMatchRunning()
    }
}

