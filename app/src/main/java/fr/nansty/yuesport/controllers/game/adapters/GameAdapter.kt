package fr.nansty.yuesport.controllers.game.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.nansty.yuesport.R
import fr.nansty.yuesport.controllers.Game

//GameAdapter affiche seulement les données (liste de JEUX)
//Listerner qui seras appeler lorsqu'une action deras fait sur une des items
class GameAdapter(private val games: List<Game>, private val gameListener: GameAdapter.GameItemListener) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>(), View.OnClickListener {




    interface GameItemListener {
        fun onGameSelected(game: Game)
        fun onGameDeleted(game: Game)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //non nullable
        val cardView = itemView.findViewById<CardView>(R.id.card_view)!!
        val gameNameView = itemView.findViewById<TextView>(R.id.name)!!
        val deleteView = itemView.findViewById<View>(R.id.delete)!!
    }

    //On inflate notre item_game.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return games.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        with(holder) {
            cardView.tag = game
            cardView.setOnClickListener(this@GameAdapter)
            gameNameView.text = game.name
            deleteView.tag = game
            deleteView.setOnClickListener(this@GameAdapter)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            //si on clique sur card_view , onappelle notre listener
            R.id.card_view -> gameListener.onGameSelected(view.tag as Game)
            R.id.delete -> gameListener.onGameDeleted(view.tag as Game)
        }

    }


}