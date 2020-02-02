package com.example.android.question.model

import java.io.Serializable

/**
 * This class contains the behavior of a Game's Introduction*/
class Introduction : Serializable{

    private var messages : ArrayList<String>
    private var maxSize : Int
    private var currentPosition : Int

    /**This constructor initializes the main attributes of the intro*/
    init {
        messages = arrayListOf()
        maxSize = 0
        currentPosition = 0
    }

    companion object{
        fun newInstance() : Introduction{
            return Introduction()
        }
    }

    /**This method shows the current message
     * @return current message*/
    fun showMessage() : String{
        return messages.get(currentPosition)
    }

    /**This method confirms if exists next message to load
     * @return true if exists next message */
    fun hasNext(): Boolean {
        if (messages.isEmpty()) return false
        if (currentPosition == (maxSize-1)) return false
        return true
    }

    /**This method returns the next message, in order by insertion
     * @return the next message*/
    fun loadNextMessage(): String {
        if (this.hasNext()){
            currentPosition += 1
            return showMessage()
        }else throw ArrayIndexOutOfBoundsException("There is no message to load")
    }

    /**This method returns the previous message, in order by insertion
     * @return the previous message*/
    fun loadPreviousMessage(): String {
        if(this.maxSize > 0){
            currentPosition -= 1
            return showMessage()
        }else throw ArrayIndexOutOfBoundsException("There is no message to load")
    }

    /**This method inserts a new message in the list
     * @param message that will be inserted*/
    fun createMessage(message : String){
        messages.add(message)
        maxSize += 1
    }
}