package com.ema.jannik.logbook.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDialogFragment
import com.ema.jannik.logbook.R
import java.lang.ClassCastException

/**
 * This class build an alert dialog were you can choose how the email layout lock like.
 * @return 1 = as list, 2 = as table
 */
class EmailDialog : AppCompatDialogFragment() {
    private lateinit var listener: EmailDialogListener
    val TAG = this::class.java.name
    private var choice: Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.alertDialog_emailTitle)
            .setNegativeButton(android.R.string.cancel, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    //dismiss
                }
            })
            .setPositiveButton(android.R.string.ok, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    listener.onOkClickedEmailDialog(choice)
                }
            })
            .setSingleChoiceItems(
                resources.getStringArray(R.array.dialog_email_choice),
                0,
                object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        choice = which
                        Log.i(TAG, choice.toString())
                    }
                }
            )
        return builder.create()
    }

    interface EmailDialogListener{
        fun onOkClickedEmailDialog(choice: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as EmailDialogListener
        } catch (e: ClassCastException) {   //when the activity wh use EmailDIalog do not implement onOkClickedEmailDialog()
            throw ClassCastException(context.toString() + "must implement interface")
        }
    }
}