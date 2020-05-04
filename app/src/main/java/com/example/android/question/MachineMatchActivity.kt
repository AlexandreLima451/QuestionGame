package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.question.model.*
import com.example.android.question.model.domain.Animals
import com.example.android.question.model.list.adapter.MessageAdapter
import com.example.android.question.model.list.adapter.QuestionAdapter
import com.example.android.question.model.list.adapter.AdapterListener
import com.example.android.question.model.list.item.Message
import kotlinx.android.synthetic.main.activity_machine_match.*
import kotlinx.android.synthetic.main.activity_machine_match.btn_img_animals_list
import kotlinx.android.synthetic.main.activity_machine_match.txt_dialog

class MachineMatchActivity : AppCompatActivity() {

    private var machineMatch = MachineMatch.getInstance(this)
    private var messageMutableList = mutableListOf<Message>()
    private var messageAdapter : MessageAdapter? = null
    private var questionAdapter : QuestionAdapter? = null
    private var player1Description = ""
    private var player2Description = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_match)

        machineMatch.init()
        messageMutableList = mutableListOf()
        messageAdapter = MessageAdapter(context = this, objects = messageMutableList)
        player1Description = getString(R.string.player1_name)
        player2Description = getString(R.string.player2_name)

        val questionRecycler = question_list

        txt_dialog.adapter = messageAdapter
        questionAdapter = QuestionAdapter(this, object : AdapterListener {
            override fun onClick(content: QuestionModel, position : Int) {
                showPlayer2Text(content.text)

                showPlayer1Text(machineMatch.answerQuestion(content))

                machineMatch.removeQuestion(content)

                questionAdapter?.notifyItemRemoved(position)
                questionAdapter?.notifyItemRangeChanged(position, machineMatch.getQuantityOfQuestions())

                rel_lyt_player_options.visibility = View.VISIBLE
                question_list.visibility = View.INVISIBLE
            }
        })

        questionAdapter!!.addQuestions(machineMatch.getQuestions())

        showPlayer1Text(machineMatch.getMatchIntro())

        questionRecycler.adapter = questionAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        questionRecycler.layoutManager = layoutManager

        btn_ask_question.setOnClickListener{
            if(machineMatch.getQuantityOfQuestions() > 0){
                rel_lyt_player_options.visibility = View.INVISIBLE
                question_list.visibility = View.VISIBLE
            }else{
                showPlayer1Text(machineMatch.answerQuestion(QuestionModel("noQuestion", "")))
                btn_ask_question.visibility = View.INVISIBLE
            }
        }

        btn_img_animals_list.setOnClickListener{
            showAnimalsList()
        }

        btn_answer.setOnClickListener{
            showValidAnswer()
        }

        btn_reset.setOnClickListener{
            resetGame()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val resultModel = data!!.getSerializableExtra("FINAL_RESULT") as ResultModel
        if(!resultModel.animal.equals(Animals.none)){
            showPlayer2Text(resultModel.resultText)
            finishGame(resultModel)
        }
    }

    /**Method that scrolls to the last message*/
    private fun scrollingDialog(){
        val countMessageAdapter = messageAdapter?.count
        if (countMessageAdapter != null) {
            txt_dialog.setSelection(countMessageAdapter.minus(1))
        }
    }

    /**Method that shows the machine's text*/
    private fun showPlayer1Text(answerPlayer1: String){
        messageMutableList.add(Message(senderName = player1Description,
                content = answerPlayer1,
                isThePlayer = false))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    /**Method that shows the player's text*/
    private fun showPlayer2Text(answerPlayer2 : String){
        messageMutableList.add(Message(senderName = player2Description,
                content = answerPlayer2,
                isThePlayer = true))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    /**
     * Method that shows the animal list
     */
    private fun showAnimalsList(){
        val intent = Intent(applicationContext, AnimalListActivity::class.java)
        startActivity(intent)
    }

    /**
     * Method that shows a list of possible answers (animals) for the player
     */
    private fun showValidAnswer(){
        val intent = Intent(applicationContext, AnimalOptionListActivity::class.java)
        intent.putExtra("ORIGIN", "MachineMatch")
        startActivityForResult(intent, 0x9988)
    }

    /**
     * This method finishes the game
     */
    private fun finishGame(result : ResultModel){
        showPlayer1Text(machineMatch.finish(result))

        btn_ask_question.visibility = View.INVISIBLE
        btn_answer.visibility = View.INVISIBLE
        btn_reset.visibility = View.VISIBLE
    }

    /**
     * This method initiates a new match
     */
    private fun resetGame(){
        btn_ask_question.visibility = View.VISIBLE
        btn_answer.visibility = View.VISIBLE
        btn_reset.visibility = View.INVISIBLE

        machineMatch.reset()
        questionAdapter!!.addQuestions(machineMatch.getQuestions())
        showPlayer1Text(machineMatch.getMatchIntro())
    }

}
