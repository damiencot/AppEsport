package fr.nansty.yuesport.controllers.esport.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.nansty.yuesport.R

class EsportFragment : Fragment(){


    companion object{
        val EXTRA_GAME_NAME = "fr.nansty.yuesport.extras.EXTRA_GAME_NAME"
        fun newInstance() = EsportFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_esport, container, false)
        return view
    }
}