[app](../../index.md) / [com.ema.jannik.logbook.fragment](../index.md) / [TimePickerFragment](index.md) / [onCreateDialog](./on-create-dialog.md)

# onCreateDialog

`open fun onCreateDialog(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Dialog`](https://developer.android.com/reference/android/app/Dialog.html)

Override to build your own custom Dialog container.  This is typically
used to show an AlertDialog instead of a generic Dialog; when doing so,
[.onCreateView](#) does not need
to be implemented since the AlertDialog takes care of its own content.

This method will be called after [.onCreate](#) and
before [.onCreateView](#).  The
default implementation simply instantiates and returns a [Dialog](https://developer.android.com/reference/android/app/Dialog.html)
class.

*Note: DialogFragment own the [ Dialog.setOnCancelListener](https://developer.android.com/reference/android/app/Dialog.html#setOnCancelListener(android.content.DialogInterface.OnCancelListener)) and [ Dialog.setOnDismissListener](https://developer.android.com/reference/android/app/Dialog.html#setOnDismissListener(android.content.DialogInterface.OnDismissListener)) callbacks.  You must not set them yourself.*
To find out about these events, override [.onCancel](#)
and [.onDismiss](#).

### Parameters

`savedInstanceState` - The last saved instance state of the Fragment,
or null if this is a freshly created Fragment.

**Return**
Return a new Dialog instance to be displayed by the Fragment.

