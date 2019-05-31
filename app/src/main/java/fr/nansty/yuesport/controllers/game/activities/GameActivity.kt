package fr.nansty.yuesport.controllers.game.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fr.nansty.yuesport.R
import fr.nansty.yuesport.controllers.Game
import fr.nansty.yuesport.controllers.esport.activities.EsportActivity
import fr.nansty.yuesport.controllers.esport.fragments.EsportFragment
import fr.nansty.yuesport.controllers.game.fragments.GameFragment
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), GameFragment.GameFragmentListener {

    private lateinit var gameFragment: GameFragment
    private var currentGame : Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameFragment = supportFragmentManager.findFragmentById(R.id.game_fragment) as GameFragment
        gameFragment.listener = this


    }

    //Selection du jeux
    override fun onGameSelected(game: Game) {
        currentGame = game
        startGameActivity(game)
    }

    //Intent => Esport
    private fun startGameActivity(game: Game) {
        val intent = Intent(this, EsportActivity::class.java)
        intent.putExtra(EsportFragment.EXTRA_GAME_NAME, game.name)
        startActivity(intent)
    }
}
