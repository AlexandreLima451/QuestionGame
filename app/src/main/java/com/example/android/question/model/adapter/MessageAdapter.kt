package com.example.android.question.model.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.android.question.R
import com.example.android.question.model.Message

class MessageAdapter(context: Context, resource: Int, objects: MutableList<Message>) : ArrayAdapter<Message>(context, resource, objects) {

    constructor(context: Context, objects: MutableList<Message>) : this (context, 0, objects)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var messageListView = convertView

        if(messageListView == null){
            messageListView = LayoutInflater.from(context)
                    .inflate(R.layout.dialog_message_item, parent, false)
        }

        val message = getItem(position)

        val messageSender = messageListView?.findViewById(R.id.message_sender) as TextView
        val messageContent = messageListView.findViewById(R.id.message_content) as TextView

        message?.let {
            messageSender.text = message.senderName
            messageContent.text = message.content

            if(message.isThePlayer){
                messageSender.gravity = Gravity.END
                messageContent.gravity = Gravity.END
            }else{
                messageSender.gravity = Gravity.START
                messageContent.gravity = Gravity.START
            }
        }

        return messageListView
    }
}