package com.example.android.question.model

interface Match {

    fun init()
    fun finish(result : ResultModel) : String
    fun isRunning() : Boolean
}