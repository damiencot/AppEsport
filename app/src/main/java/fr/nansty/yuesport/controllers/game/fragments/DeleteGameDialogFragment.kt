package fr.nansty.yuesport.controllers.game.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import fr.nansty.yuesport.R

class DeleteGameDialogFragment : DialogFragment(){

    interface DeleteGameDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    companion object {
        val EXTRA_GAME_NAME = "fr.nansty.yuesport.extras.EXTRA_GAME_NAME"

        fun newInstance(gameName: String): DeleteGameDialogFragment {
            val fragment = DeleteGameDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(EXTRA_GAME_NAME, gameName)
            }

            return fragment
        }
    }

    var listener: DeleteGameDialogListener? = null

    private lateinit var gameName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameName = arguments!!.getString(EXTRA_GAME_NAME)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)

        builder.setTitle(getString(R.string.deletegame_title, gameName))
            .setPositiveButton(getString(R.string.deletegame_positive),
                { _, _ -> listener?.onDialogPositiveClick() })
            .setNegativeButton(getString(R.string.deletegame_negative),
                { _, _ -> listener?.onDialogNegativeClick() })

        return builder.create()
    }
}