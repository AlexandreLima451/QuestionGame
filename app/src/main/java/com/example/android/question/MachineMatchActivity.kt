package com.example.android.question

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.android.question.model.Message
import com.example.android.question.model.adapter.MessageAdapter
import com.example.android.question.model.QuestionModel
import com.example.android.question.model.Results
import com.example.android.question.model.adapter.QuestionAdapter
import com.example.android.question.model.adapter.AdapterListener
import kotlinx.android.synthetic.main.activity_machine_match.*
import kotlinx.android.synthetic.main.activity_machine_match.txt_dialog

class MachineMatchActivity : AppCompatActivity() {

    private var messageMutableList = mutableListOf<Message>()
    private var messageAdapter : MessageAdapter? = null
    private var player1Description = ""
    private var player2Description = ""
    private var chosenAnimal = Results.newInstance().get("LION")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_match)

        messageMutableList = mutableListOf()
        messageAdapter = MessageAdapter(context = this, objects = messageMutableList)
        player1Description = getString(R.string.player1_name)
        player2Description = getString(R.string.player2_name)

        txt_dialog.adapter = messageAdapter

        val questions = mutableListOf<QuestionModel>()
        questions.add(QuestionModel("mammal", getString(R.string.opt_quest_mammal)))
        questions.add(QuestionModel("herbivore", getString(R.string.opt_quest_herbivore)))
        questions.add(QuestionModel("quadruped", getString(R.string.opt_quest_quadruped)))
        questions.add(QuestionModel("carnivore", getString(R.string.opt_quest_carnivore)))
        questions.add(QuestionModel("fins", getString(R.string.opt_quest_fins)))
        questions.add(QuestionModel("flying", getString(R.string.opt_quest_flying)))

        val questionRecycler = question_list
        questionRecycler.adapter = QuestionAdapter(questions, this, object : AdapterListener {
            override fun onClick(content: QuestionModel) {
                showPlayer2Text(content.text)
                val answer = answerQuestion(content)
                if (answer) {
                    showPlayer1Text("Sim")
                } else {
                    showPlayer1Text("Não")
                }

            }
        })

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        questionRecycler.layoutManager = layoutManager
    }

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

    fun answerQuestion(question : QuestionModel) : Boolean{
        var answer = false

        chosenAnimal!!.animal.let {
            when(question.title){
                "mammal" -> answer = it.isMammal
                "quadruped" -> answer = it.isQuadruped
                "carnivore" -> answer = it.isCarnivore
                "herbivore" -> answer = it.isHerbivore
                "flying" -> answer = it.isFlying
                "fins" -> answer = it.hasFins
            }
        }
        return answer
    }
}
