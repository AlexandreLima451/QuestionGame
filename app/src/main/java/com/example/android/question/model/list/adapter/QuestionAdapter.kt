package com.example.android.question.model.list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.question.R
import com.example.android.question.model.QuestionModel
import kotlinx.android.synthetic.main.question_list_item.view.*

class QuestionAdapter (/*private val questions : List<QuestionModel>,
                       */private val context : Context, private val listener: AdapterListener) : Adapter<QuestionAdapter.ViewHolder>(){


    private var AdapterQuestions : MutableList<QuestionModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.question_list_item, parent, false)
        return ViewHolder(view)
    }

    fun addQuestions(questions: MutableList<QuestionModel>) {
        AdapterQuestions = questions as MutableList<QuestionModel>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return AdapterQuestions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = AdapterQuestions[position]
        holder.bindView(question, listener, holder.adapterPosition)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindView(question : QuestionModel, listener: AdapterListener, position : Int){
            itemView.btn_question.text  = question.text
            itemView.btn_question.tag = position
            itemView.btn_question.setOnClickListener{
                listener.onClick(question, position)
            }
        }
    }
}

interface AdapterListener {
    fun onClick(content: QuestionModel, position : Int)
}