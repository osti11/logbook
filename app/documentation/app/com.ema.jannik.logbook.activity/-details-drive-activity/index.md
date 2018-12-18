[app](../../index.md) / [com.ema.jannik.logbook.activity](../index.md) / [DetailsDriveActivity](./index.md)

# DetailsDriveActivity

`class DetailsDriveActivity : AppCompatActivity, OnMapReadyCallback`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DetailsDriveActivity()` |

### Properties

| Name | Summary |
|---|---|
| [TAG](-t-a-g.md) | `val TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateOptionsMenu](on-create-options-menu.md) | `fun onCreateOptionsMenu(menu: `[`Menu`](https://developer.android.com/reference/android/view/Menu.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>set the menu on top. |
| [onMapReady](on-map-ready.md) | `fun onMapReady(googleMap: GoogleMap): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add markers for the start address and the destination. |
| [onOptionsItemSelected](on-options-item-selected.md) | `fun onOptionsItemSelected(item: `[`MenuItem`](https://developer.android.com/reference/android/view/MenuItem.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>call the function saveDrive() when save in the right top corner is clicked. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [ERROR_DIALOG_REQUEST](-e-r-r-o-r_-d-i-a-l-o-g_-r-e-q-u-e-s-t.md) | `const val ERROR_DIALOG_REQUEST: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>to identify the error dialog |
| [EXTRA__ID](-e-x-t-r-a__-i-d.md) | `const val EXTRA__ID: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>to identify the extra from the intent. |
