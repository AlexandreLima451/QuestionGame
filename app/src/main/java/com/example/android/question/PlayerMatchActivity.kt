package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_player_match.*
import com.example.android.question.model.PlayerMatch
import com.example.android.question.model.Message
import com.example.android.question.model.adapter.MessageAdapter

/**
 * This class handles with the game's logic
 */

class PlayerMatchActivity : AppCompatActivity() {

    private var match = PlayerMatch()
    private var messageMutableList = mutableListOf<Message>()
    private var messageAdapter : MessageAdapter? = null
    private var player1Description = ""
    private var player2Description = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_match)

        messageMutableList = mutableListOf()
        messageAdapter = MessageAdapter(context = this, objects = messageMutableList)
        player1Description = getString(R.string.player1_name)
        player2Description = getString(R.string.player2_name)

        txt_dialog.adapter = messageAdapter

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

        match.init()
        showPlayer1Text(match.currentQuestion.text)
    }

    private fun showAnimalsList(){
        val intent = Intent(applicationContext, AnimalListActivity::class.java)
        startActivity(intent)
    }

    private fun showPlayer1Text(answerPlayer1: String){
        messageMutableList.add(Message(senderName = player1Description,
                                       content = answerPlayer1,
                                       isThePlayer = false))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    private fun showPlayer2Text(answerPlayer2 : Boolean){
        val player2Answer : String = if(answerPlayer2){
            getString(R.string.yes_answer)
        }else{
            getString(R.string.no_answer)
        }

        messageMutableList.add(Message(senderName = player2Description,
                content = player2Answer,
                isThePlayer = true))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    private fun scrollingDialog(){
        val countMessageAdapter = messageAdapter?.count
        if (countMessageAdapter != null) {
            txt_dialog.setSelection(countMessageAdapter.minus(1))
        }
    }

    /**
     * This method sets the answer "yes" and loads a new question
     */
    private fun clickYes(){
        match.setAnswer(true)
        showPlayer2Text(true)
        if(match.checkResult(true)){
            finishGame()
        }else{
            if(match.loadQuestion()){
                showPlayer1Text(match.currentQuestion.text)
            }else{
                finishGame()
            }
        }
    }

    /**
     * This method sets the answer "no" and loads a new question
     */
    private fun clickNo(){
        match.setAnswer(false)
        showPlayer2Text(false)
        if(match.checkResult(false)){
            finishGame()
        }else{
            if(match.loadQuestion()){
                showPlayer1Text(match.currentQuestion.text)
            }else{
                finishGame()
            }
        }
    }

    /**
     * This method finishes the game
     */
    private fun finishGame(){
        //txt_dialog.text = match.finish()
        showPlayer1Text(match.finish())

        btn_yes.visibility = View.INVISIBLE
        btn_no.visibility = View.INVISIBLE
        btn_reset.visibility = View.VISIBLE
    }

    /**
     * This method initiates a new match
     */
    private fun resetGame(){
        btn_yes.visibility = View.VISIBLE
        btn_no.visibility = View.VISIBLE
        btn_reset.visibility = View.INVISIBLE

        match.reset()
        showPlayer1Text(match.currentQuestion.text)
    }
}
