package fr.nansty.yuesport.controllers.esport.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import fr.nansty.yuesport.controllers.esport.fragments.EsportFragment

class EsportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, EsportFragment.newInstance()).commit()

    }
}