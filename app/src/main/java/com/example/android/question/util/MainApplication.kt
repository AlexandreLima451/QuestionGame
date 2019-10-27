package com.example.android.question.util

/**Original code by
 * @author paraya3636
 *
 * See: https://gist.github.com/paraya3636/bf8108a75eb49323e56c0c90dd0747e0
 * Not object class. AndroidManifest.xml error happen.
 * */

import android.app.Application
import android.content.Context

class MainApplication : Application(){

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        val context = applicationContext()
    }
}