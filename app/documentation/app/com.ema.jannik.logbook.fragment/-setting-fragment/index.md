[app](../../index.md) / [com.ema.jannik.logbook.fragment](../index.md) / [SettingFragment](./index.md)

# SettingFragment

`class SettingFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SettingFragment()` |

### Functions

| Name | Summary |
|---|---|
| [onActivityCreated](on-activity-created.md) | `fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when the fragment's activity has been created and this fragment's view hierarchy instantiated.  It can be used to do final initialization once these pieces are in place, such as retrieving views or restoring state.  It is also useful for fragments that use [.setRetainInstance](#) to retain their instance, as this callback tells the fragment when it is fully associated with the new activity instance.  This is called after [.onCreateView](#) and before [.onViewStateRestored](#). |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?`<br>Inflate the layout for this fragment. |

### Companion Object Functions

| Name | Summary |
|---|---|
| [getItemLayout](get-item-layout.md) | `fun getItemLayout(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
