package com.ema.jannik.logbook.fragment

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.util.*

open class TimePickerFragment: DialogFragment() {
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
        val calander = Calendar.getInstance()
        val hour: Int = calander.get(Calendar.HOUR_OF_DAY)
        val min: Int = calander.get(Calendar.MINUTE)
        //send Time directly to the AddDriceActivity
        return TimePickerDialog(activity, activity as TimePickerDialog.OnTimeSetListener, hour, min,
            android.text.format.DateFormat.is24HourFormat(activity))
    }
}