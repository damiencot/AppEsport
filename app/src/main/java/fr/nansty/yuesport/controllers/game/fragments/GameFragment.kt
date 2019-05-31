package fr.nansty.yuesport.controllers.game.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import fr.nansty.yuesport.Database

import fr.nansty.yuesport.R
import fr.nansty.yuesport.controllers.Game
import fr.nansty.yuesport.controllers.game.activities.App
import fr.nansty.yuesport.controllers.game.adapters.GameAdapter
import fr.nansty.yuesport.utils.toast


class GameFragment : Fragment(), GameAdapter.GameItemListener {

    interface GameFragmentListener{
        fun onGameSelected(game: Game)
    }

    var listener : GameFragmentListener? = null

    private lateinit var games: MutableList<Game>

    //Réference Database
    private lateinit var database: Database

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        games = mutableListOf()
        //Ce fragment auras une options menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        recyclerView = view.findViewById(R.id.games_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_game, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        games = database.getAllGames()
        adapter = GameAdapter(games, this)
        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_create_game -> {
                showCreateGameDialog()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showCreateGameDialog() {
        val createGameFragment = CreateGameDialogFragment()
        createGameFragment.listener = object : CreateGameDialogFragment.CreateGameDialogListener {
            override fun onDialogPositiveClick(gameName: String) {
                saveGame(Game(gameName))
            }

            override fun onDIalogNegativeClick() {
            }
        }
        createGameFragment.show(fragmentManager, "CreateGameDialogFragment")
    }

    private fun saveGame(game: Game) {
        if (database.createGame(game)) {
            games.add(game)
            adapter.notifyDataSetChanged()
        } else {
            context?.toast(getString(R.string.game_message_error_could_note_create_game))
        }
    }

    override fun onGameSelected(game: Game) {
        listener?.onGameSelected(game)
    }

    override fun onGameDeleted(game: Game) {
        showDeleteGameDialog(game)
    }

    private fun showDeleteGameDialog(game: Game) {
        val deleteGameFragment = DeleteGameDialogFragment.newInstance(game.name)
        deleteGameFragment.listener = object : DeleteGameDialogFragment.DeleteGameDialogListener {
            override fun onDialogPositiveClick() {
                deleteGame(game)
            }

            override fun onDialogNegativeClick() {
            }

        }
        deleteGameFragment.show(fragmentManager, "DeleteGameDialogFragment")
    }

    private fun deleteGame(game: Game) {
        if (database.deleteGame(game)) {
            games.remove(game)
            adapter.notifyDataSetChanged()
            context?.toast(getString(R.string.game_message_info_game_deleted, game.name))
        } else {
            context?.toast(getString(R.string.game_message_error_game_deleted, game.name))
        }
    }


}
