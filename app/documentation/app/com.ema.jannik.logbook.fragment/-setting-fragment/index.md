[app](../../index.md) / [com.ema.jannik.logbook.fragment](../index.md) / [SettingFragment](./index.md)

# SettingFragment

`class SettingFragment : Fragment, `[`OnItemSelectedListener`](https://developer.android.com/reference/android/widget/AdapterView/OnItemSelectedListener.html)`, `[`OnClickListener`](https://developer.android.com/reference/android/view/View/OnClickListener.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SettingFragment()` |

### Properties

| Name | Summary |
|---|---|
| [TAG](-t-a-g.md) | `val TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [onActivityCreated](on-activity-created.md) | `fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when the fragment's activity has been created and this fragment's view hierarchy instantiated. |
| [onClick](on-click.md) | `fun onClick(v: `[`View`](https://developer.android.com/reference/android/view/View.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when button_setDefault has been clicked. |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do initial creation of a fragment.  This is called after [.onAttach](#) and before [.onCreateView](#). |
| [onCreateOptionsMenu](on-create-options-menu.md) | `fun onCreateOptionsMenu(menu: `[`Menu`](https://developer.android.com/reference/android/view/Menu.html)`?, inflater: `[`MenuInflater`](https://developer.android.com/reference/android/view/MenuInflater.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Initialize the contents of the Fragment host's standard options menu. |
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?`<br>Inflate the layout for this fragment. |
| [onItemSelected](on-item-selected.md) | `fun onItemSelected(parent: `[`AdapterView`](https://developer.android.com/reference/android/widget/AdapterView.html)`<*>?, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Callback method to be invoked when an item in this view has been selected. This callback is invoked only when the newly selected position is different from the previously selected position or if there was no selected item. |
| [onNothingSelected](on-nothing-selected.md) | `fun onNothingSelected(parent: `[`AdapterView`](https://developer.android.com/reference/android/widget/AdapterView.html)`<*>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Callback method to be invoked when the selection disappears from this view. The selection can disappear for instance when touch is activated or when the adapter becomes empty. |
| [onOptionsItemSelected](on-options-item-selected.md) | `fun onOptionsItemSelected(item: `[`MenuItem`](https://developer.android.com/reference/android/view/MenuItem.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>call the function saveSettings() when save in the right top corner is clicked. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [LAYOUT_FOUR](-l-a-y-o-u-t_-f-o-u-r.md) | `const val LAYOUT_FOUR: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [LAYOUT_ONE](-l-a-y-o-u-t_-o-n-e.md) | `const val LAYOUT_ONE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [LAYOUT_THREE](-l-a-y-o-u-t_-t-h-r-e-e.md) | `const val LAYOUT_THREE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [LAYOUT_TWO](-l-a-y-o-u-t_-t-w-o.md) | `const val LAYOUT_TWO: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [NOTIFICATIN](-n-o-t-i-f-i-c-a-t-i-n.md) | `const val NOTIFICATIN: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>shared preference storage the value of spinner_notificationInterval. |
| [PURPOSE](-p-u-r-p-o-s-e.md) | `const val PURPOSE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>shared preferences speichert den Zustand von switch_purpose. Dieser legt fest es eine Benachrichtigung gibt wenn 'Zweck der Fahrt' nicht angegeben wird. |
| [SHARED_PREFERENCES](-s-h-a-r-e-d_-p-r-e-f-e-r-e-n-c-e-s.md) | `const val SHARED_PREFERENCES: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Name of the shared preferences. |
