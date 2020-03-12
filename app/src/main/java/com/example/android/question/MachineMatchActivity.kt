package com.example.android.question

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.android.question.model.MachineMatch
import com.example.android.question.model.Message
import com.example.android.question.model.adapter.MessageAdapter
import com.example.android.question.model.QuestionModel
import com.example.android.question.model.adapter.QuestionAdapter
import com.example.android.question.model.adapter.AdapterListener
import kotlinx.android.synthetic.main.activity_machine_match.*
import kotlinx.android.synthetic.main.activity_machine_match.btn_img_animals_list
import kotlinx.android.synthetic.main.activity_machine_match.txt_dialog

class MachineMatchActivity : AppCompatActivity() {

    private var machineMatch = MachineMatch(this)
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
        questionAdapter = QuestionAdapter(machineMatch.getQuestions(), this, object : AdapterListener {
            override fun onClick(content: QuestionModel, position : Int) {
                showPlayer2Text(content.text)

                val answerText = getAnswerText(content.title)

                if (machineMatch.answerQuestion(content)) { // In an affirmative case...
                    showPlayer1Text(answerText)
                } else {
                    showPlayer1Text(getString(R.string.mach_negative_answer))
                }

                machineMatch.removeQuestion(content)
                //questionRecycler.removeViewAt(pos)
                questionAdapter?.notifyItemRemoved(position)
                questionAdapter?.notifyItemRangeChanged(position, machineMatch.getQuantityOfQuestions())
                //questionAdapter?.notifyDataSetChanged()

            }
        })

        questionRecycler.adapter = questionAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        questionRecycler.layoutManager = layoutManager

        btn_ask_question.setOnClickListener{
            rel_lyt_ask_player.visibility = View.INVISIBLE
            question_list.visibility = View.VISIBLE
        }

        btn_img_animals_list.setOnClickListener{
            showAnimalsList()
        }
    }

    private fun getAnswerText(animalBreed: String): String {
        var answerText = "none"

        when(animalBreed){
            "mammal" -> answerText = this.getString(R.string.answer_animal_is_mammal)
            "quadruped" -> answerText = this.getString(R.string.answer_animal_is_quadruped)
            "carnivore" -> answerText = this.getString(R.string.answer_animal_is_carnivore)
            "herbivore" -> answerText = this.getString(R.string.answer_animal_is_herbivore)
            "flying" -> answerText = this.getString(R.string.answer_animal_is_flying)
            "fins" -> answerText = this.getString(R.string.answer_animal_has_fins)
        }

        return answerText
    }

    /*private fun updateQuestionsList() {
        val countQuestionAdapter = questionAdapter?.count
        if (countQuestionAdapter != null) {
            question_list.up.setSelection(countQuestionAdapter.minus(1))
        }
    }*/

    private fun scrollingDialog(){
        val countMessageAdapter = messageAdapter?.count
        if (countMessageAdapter != null) {
            txt_dialog.setSelection(countMessageAdapter.minus(1))
        }
    }

    private fun showPlayer1Text(answerPlayer1: String){
        messageMutableList.add(Message(senderName = player1Description,
                content = answerPlayer1,
                isThePlayer = false))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    private fun showPlayer2Text(answerPlayer2 : String){
        messageMutableList.add(Message(senderName = player2Description,
                content = answerPlayer2,
                isThePlayer = true))

        messageAdapter?.notifyDataSetChanged()
        scrollingDialog()
    }

    private fun showAnimalsList(){
        val intent = Intent(applicationContext, AnimalListActivity::class.java)
        startActivity(intent)
    }
}
