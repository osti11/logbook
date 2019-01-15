package com.ema.jannik.logbook.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.fragment.DatePickerFragment

/**
 * This class open an Dialog that explain the settings.
 * @param string This is the id of an entry in the string.xml file which will set as message.
 */
@SuppressLint("ValidFragment")
class ExplanationDialogSettings(val string: Int) : AppCompatDialogFragment(), DialogInterface.OnClickListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(R.string.alertDialog_titleSettings)
            .setMessage(string)

        alertDialogBuilder.setPositiveButton("OK", this)

        return alertDialogBuilder.create()
    }

    /**
     * close dialog when ok 'OK' clicked.
     */
    override fun onClick(dialog: DialogInterface?, which: Int) {}
}

@SuppressLint("ValidFragment")
class DeleteAllDialog() : AppCompatDialogFragment(), DialogInterface.OnClickListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(R.string.title_deleteAll)
            .setMessage(R.string.explanationDeleteAll)

        alertDialogBuilder.setPositiveButton("OK", this)

        return alertDialogBuilder.create()
    }

    /**
     * close dialog when ok 'OK' clicked.
     */
    override fun onClick(dialog: DialogInterface?, which: Int) {
        startDatePickerFragment()
    }

    private fun startDatePickerFragment() {
        val datePicker: DialogFragment = DatePickerFragment()
        datePicker.show(activity!!.supportFragmentManager, "datePickerDelete")
    }
}

/**
 * Diese Klasse wird von der AddDriveActivity verwendet, wenn falsche werte
 * abgegeben werden um den Benutzer darauf hinzuweisen.
 * @param string Dieser String wird als message des alertDialog gesetzt.
 */
@SuppressLint("ValidFragment")
class ExplanationDialogAddDrive(val string: String) : AppCompatDialogFragment(), DialogInterface.OnClickListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(R.string.alertDialog_titleAddDrive)
            .setMessage(string)

        alertDialogBuilder.setPositiveButton("OK", this)

        return alertDialogBuilder.create()
    }

    /**
     * close dialog when ok 'OK' clicked.
     */
    override fun onClick(dialog: DialogInterface?, which: Int) {}
}