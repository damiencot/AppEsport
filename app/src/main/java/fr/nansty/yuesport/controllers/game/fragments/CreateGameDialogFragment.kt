package fr.nansty.yuesport.controllers.game.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.InputType
import android.widget.EditText
import fr.nansty.yuesport.R

class CreateGameDialogFragment : DialogFragment() {


    //interface pour savoir si le clique est "oui" ou "non"
    interface CreateGameDialogListener {
        //@params nom de la ville saisie pour l'utilisateur
        fun onDialogPositiveClick(gameName: String)

        fun onDIalogNegativeClick()
    }


    var listener: CreateGameDialogListener? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        //champs EditText
        var input = EditText(context)
        with(input) {
            inputType = InputType.TYPE_CLASS_TEXT
            hint = context.getString(R.string.creategame_gamehint)
        }

        builder.setTitle(getString(R.string.creategame_title))
            .setView(input)
            .setPositiveButton(getString(R.string.creategame_positive),
                DialogInterface.OnClickListener { _, _ ->
                    listener?.onDialogPositiveClick(input.text.toString())
                })
            .setNegativeButton(getString(R.string.creategame_negative),
                DialogInterface.OnClickListener { dialog, _ -> dialog.cancel() })

        return builder.create()
    }
}