package com.ema.jannik.logbook.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment: DialogFragment() {
    /**
     * Override to build your own custom Dialog container.  This is typically
     * used to show an AlertDialog instead of a generic Dialog; when doing so,
     * [.onCreateView] does not need
     * to be implemented since the AlertDialog takes care of its own content.
     *
     *
     * This method will be called after [.onCreate] and
     * before [.onCreateView].  The
     * default implementation simply instantiates and returns a [Dialog]
     * class.
     *
     *
     * *Note: DialogFragment own the [ Dialog.setOnCancelListener][Dialog.setOnCancelListener] and [ Dialog.setOnDismissListener][Dialog.setOnDismissListener] callbacks.  You must not set them yourself.*
     * To find out about these events, override [.onCancel]
     * and [.onDismiss].
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return Return a new Dialog instance to be displayed by the Fragment.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month= c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, activity as DatePickerDialog.OnDateSetListener, year, month, day)
    }
}