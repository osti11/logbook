[app](../../index.md) / [com.ema.jannik.logbook.fragment](../index.md) / [OverviewFragment](index.md) / [onCreateView](./on-create-view.md)

# onCreateView

`fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?`

Called to have the fragment instantiate its user interface view.
This is optional, and non-graphical fragments can return null (which
is the default implementation).  This will be called between
[.onCreate](#) and [.onActivityCreated](#).

If you return a View from here, you will later be called in
[.onDestroyView](#) when the view is being released.

### Parameters

`inflater` - The LayoutInflater object that can be used to inflate
any views in the fragment,

`container` - If non-null, this is the parent view that the fragment's
UI should be attached to.  The fragment should not add the view itself,
but this can be used to generate the LayoutParams of the view.

`savedInstanceState` - If non-null, this fragment is being re-constructed
from a previous saved state as given here.

**Return**
Return the View for the fragment's UI, or null.

