[app](../../index.md) / [com.ema.jannik.logbook.fragment](../index.md) / [SettingFragment](index.md) / [onActivityCreated](./on-activity-created.md)

# onActivityCreated

`fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called when the fragment's activity has been created and this
fragment's view hierarchy instantiated.  It can be used to do final
initialization once these pieces are in place, such as retrieving
views or restoring state.  It is also useful for fragments that use
[.setRetainInstance](#) to retain their instance,
as this callback tells the fragment when it is fully associated with
the new activity instance.  This is called after [.onCreateView](#)
and before [.onViewStateRestored](#).

### Parameters

`savedInstanceState` - If the fragment is being re-created from
a previous saved state, this is the state.