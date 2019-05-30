package fr.nansty.yuesport.game.controllers.activities

import android.app.Application
import fr.nansty.yuesport.Database

class App : Application(){

    companion object{
        lateinit var instance : App

        val database: Database by lazy { Database(instance) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}