[app](../../index.md) / [com.ema.jannik.logbook](../index.md) / [MainActivity](./index.md)

# MainActivity

`class MainActivity : AppCompatActivity, OnNavigationItemSelectedListener`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MainActivity()` |

### Functions

| Name | Summary |
|---|---|
| [onBackPressed](on-back-pressed.md) | `fun onBackPressed(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onClickFabAdd](on-click-fab-add.md) | `fun onClickFabAdd(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>this function is called when the floating action button 'fab_add' is clicked and start an AddDriveActivity. |
| [onClickFabRec](on-click-fab-rec.md) | `fun onClickFabRec(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>this function is called when the floating action button 'fab_record' is clicked and start an RecordDriveActivity. |
| [onClickFabShow](on-click-fab-show.md) | `fun onClickFabShow(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateOptionsMenu](on-create-options-menu.md) | `fun onCreateOptionsMenu(menu: `[`Menu`](https://developer.android.com/reference/android/view/Menu.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>set the menu 'overview_menu' which contains the share button in the top right corner |
| [onNavigationItemSelected](on-navigation-item-selected.md) | `fun onNavigationItemSelected(item: `[`MenuItem`](https://developer.android.com/reference/android/view/MenuItem.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Called when an item in the navigation menu is selected. |
| [onOptionsItemSelected](on-options-item-selected.md) | `fun onOptionsItemSelected(item: `[`MenuItem`](https://developer.android.com/reference/android/view/MenuItem.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>call sendMail() when the share button is clicked |
| [sendOnChannel](send-on-channel.md) | `fun sendOnChannel(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Send notification on channel |
