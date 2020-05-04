package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.android.question.model.*
import com.example.android.question.model.domain.Animals
import com.example.android.question.model.list.adapter.AdapterListener
import com.example.android.question.model.list.adapter.MessageAdapter
import com.example.android.question.model.list.adapter.QuestionAdapter
import com.example.android.question.model.list.item.Message
import kotlinx.android.synthetic.main.activity_versus_match.*

class VersusMatchActivity : AppCompatActivity() {

    private var versusMatch = VersusMatch.getInstance(this)
    private var playerMatch : PlayerMatch? = null
    private var machineMatch : MachineMatch? = null
    private var messageMutableList = mutableListOf<Message>()
    private var messageAdapter : MessageAdapter? = null
    private var questionAdapter : QuestionAdapter? = null
    private var player1Description = ""
    private var player2Description = ""
    private var questionRecycler : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_versus_match)

        messageMutableList = mutableListOf()
        messageAdapter = MessageAdapter(context = this, objects = messageMutableList)
        player1Description = getString(R.string.player1_name)
        player2Description = getString(R.string.player2_name)

        txt_dialog.adapter = messageAdapter

        questionRecycler = question_list
        questionAdapter = QuestionAdapter(this, object : AdapterListener {
            override fun onClick(content: QuestionModel, position : Int) {
                showPlayer2Text(content.text)

                showPlayer1Text(machineMatch!!.answerQuestion(content))

                machineMatch!!.removeQuestion(content)

                questionAdapter?.notifyItemRemoved(position)
                questionAdapter?.notifyItemRangeChanged(position, machineMatch!!.getQuantityOfQuestions())

                rel_lyt_player_options.visibility = View.VISIBLE
                question_list.visibility = View.INVISIBLE
            }
        })

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        questionRecycler!!.layoutManager = layoutManager

        btn_ask_question.setOnClickListener{
            if(machineMatch!!.getQuantityOfQuestions() > 0){
                rel_lyt_player_options.visibility = View.INVISIBLE
                question_list.visibility = View.VISIBLE
            }else{
                showPlayer1Text(machineMatch!!.answerQuestion(QuestionModel("noQuestion", "")))
                btn_ask_question.visibility = View.INVISIBLE
            }
        }

        btn_answer.setOnClickListener{
            showValidAnswer()
        }

        btn_yes.setOnClickListener {
            clickYes()
        }

        btn_no.setOnClickListener {
            clickNo()
        }

        btn_reset.setOnClickListener {
            resetGame()
        }

        btn_img_animals_list.setOnClickListener{
            showAnimalsList()
        }

        versusMatch.init()
        configureMatch()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val resultModel = data!!.getSerializableExtra("FINAL_RESULT") as ResultModel
        if(!resultModel.animal.equals(Animals.none)){
            showPlayer2Text(resultModel.resultText)
            finishMachineMatch(resultModel)
        }
    }

    /**
     * Method that sets the options according the current match's type
     */
    private fun configureMatch(){
        if(versusMatch.isPlayerMatch()){
            machineMatch = null
            playerMatch = versusMatch.convertCurrentMatchToPlayer()
            playerMatch!!.init()
            rendersPlayerMatch()
            showPlayer1Text(playerMatch!!.currentQuestion.text)
        }else if (versusMatch.isMachineMatch()){
            playerMatch = null
            machineMatch = versusMatch.convertCurrentMatchToComputer()
            machineMatch!!.init()
            rendersMachineMatch()
            questionAdapter!!.addQuestions(machineMatch!!.getQuestions())
            questionRecycler!!.adapter = questionAdapter
            showPlayer1Text(machineMatch!!.getMatchIntro())
        }
    }

    /**
     * Method that shows the animal list
     */
    private fun showAnimalsList(){
        val intent = Intent(applicationContext, AnimalListActivity::class.java)
        startActivity(intent)
    }

    /**Method that shows the machine's text
     * @param answerPlayer1 is text that will be displayed */
    private fun showPlayer1Text(answerPlayer1: String){
        messageMutableList.add(Message(senderName = player1Description,
                content = answerPlayer1,
                isThePlayer = false))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    /**Method that shows the player's text
     * @param answerPlayer2 is text that will be displayed*/
    private fun showPlayer2Text(answerPlayer2 : String){
        messageMutableList.add(Message(senderName = player2Description,
                content = answerPlayer2,
                isThePlayer = true))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    /**Method that scrolls to the last message*/
    private fun scrollingDialog(){
        val countMessageAdapter = messageAdapter?.count
        if (countMessageAdapter != null) {
            txt_dialog.setSelection(countMessageAdapter.minus(1))
        }
    }

    /**Method that renders the player's options for the player mode*/
    fun rendersPlayerMatch(){
        btn_yes.visibility = View.VISIBLE
        btn_no.visibility = View.VISIBLE
        rel_lyt_player_options.visibility = View.VISIBLE

        btn_reset.visibility = View.INVISIBLE
        btn_ask_question.visibility = View.INVISIBLE
        btn_answer.visibility = View.INVISIBLE
        question_list.visibility = View.INVISIBLE
    }

    /**Method that renders the player's options for the computer mode*/
    fun rendersMachineMatch(){
        btn_ask_question.visibility = View.VISIBLE
        btn_answer.visibility = View.VISIBLE
        rel_lyt_player_options.visibility = View.VISIBLE

        btn_yes.visibility = View.INVISIBLE
        btn_no.visibility = View.INVISIBLE
        btn_reset.visibility = View.INVISIBLE
        question_list.visibility = View.INVISIBLE
    }

    /**
     * This method sets the answer "yes" and loads a new question
     */
    private fun clickYes(){

        playerMatch!!.setAnswer(true)
        showPlayer2Text(this.getString(R.string.yes_answer))
        if(playerMatch!!.checkResult(true)){
            finishPlayerMatch(true)
        }else{
            if(playerMatch!!.loadQuestion()){
                showPlayer1Text(playerMatch!!.currentQuestion.text)
            }else{
                finishPlayerMatch(false)
            }
        }
    }

    /**
     * This method sets the answer "no" and loads a new question
     */
    private fun clickNo(){
        playerMatch!!.setAnswer(false)
        showPlayer2Text(this.getString(R.string.no_answer))
        if(playerMatch!!.checkResult(false)){
            finishPlayerMatch(true)
        }else{
            if(playerMatch!!.loadQuestion()){
                showPlayer1Text(playerMatch!!.currentQuestion.text)
            }else{
                finishPlayerMatch(false)
            }
        }
    }

    /**
     * This method finishes the player's match
     * @param isCorrect is true if the computer wins the match
     */
    private fun finishPlayerMatch(isCorrect : Boolean){
        versusMatch.countMachinePoints(isCorrect)
        showPlayer1Text(playerMatch!!.finishMatch())
        updateScore()
        if(versusMatch.hasNextRound()){
            versusMatch.nextRound()
        }else{
            finishVersusMatch()
        }

        configureMatch()
    }

    fun finishVersusMatch(){
        btn_reset.visibility = View.VISIBLE
        rel_lyt_player_options.visibility = View.VISIBLE

        btn_yes.visibility = View.INVISIBLE
        btn_no.visibility = View.INVISIBLE
        btn_ask_question.visibility = View.INVISIBLE
        btn_answer.visibility = View.INVISIBLE
        question_list.visibility = View.INVISIBLE

        val finalResult = versusMatch.finish()
        showPlayer1Text(finalResult.resultText)
    }

    /**
     * This method finishes the computer's match
     * @param result chosen by the player
     */
    private fun finishMachineMatch(result : ResultModel){
        val isCorrect = if(machineMatch!!.chosenAnimal.equals(Animals.none)){
            true
        }else{
            machineMatch!!.checkAnswer(result)
        }
        versusMatch.countPlayerPoints(isCorrect)
        showPlayer1Text(machineMatch!!.finish(result))
        updateScore()
        if(versusMatch.hasNextRound()){
            versusMatch.nextRound()
        }else{
            finishVersusMatch()
        }
        configureMatch()
    }

    /**
     * Method that shows a list of possible answers (animals) for the player
     */
    private fun showValidAnswer(){
        val intent = Intent(applicationContext, AnimalOptionListActivity::class.java)
        intent.putExtra("ORIGIN", "VersusMatch")
        startActivityForResult(intent, 0x9988)
    }

    /**
     * Method that updates the score on the top of activity
     */
    private fun updateScore(){
        match_score.text = this.getString(R.string.versus_mode_score, versusMatch.getPlayerScore(), versusMatch.getMachineScore())
    }

    /**
     * This method initiates a new match
     */
    private fun resetGame(){
        versusMatch.init()
        configureMatch()
        updateScore()
    }
}
