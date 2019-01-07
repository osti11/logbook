package com.ema.jannik.logbook.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.ema.jannik.logbook.R

@SuppressLint("ValidFragment")
class ExplanationDialog(val string: Int) : AppCompatDialogFragment(), DialogInterface.OnClickListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(R.string.alertDialog_title)
            .setMessage(string)

        alertDialogBuilder.setPositiveButton("OK", this)

        return alertDialogBuilder.create()
    }

    /**
     * close dialog when ok 'OK' clicked.
     */
    override fun onClick(dialog: DialogInterface?, which: Int) {}
}