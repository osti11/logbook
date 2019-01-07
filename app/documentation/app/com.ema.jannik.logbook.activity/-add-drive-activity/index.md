[app](../../index.md) / [com.ema.jannik.logbook.activity](../index.md) / [AddDriveActivity](./index.md)

# AddDriveActivity

`class AddDriveActivity : AppCompatActivity, `[`OnTimeSetListener`](https://developer.android.com/reference/android/app/TimePickerDialog/OnTimeSetListener.html)

in this Activity the user can add a past ride to the db.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AddDriveActivity()`<br>in this Activity the user can add a past ride to the db. |

### Properties

| Name | Summary |
|---|---|
| [category](category.md) | `var category: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>represent the chosen imageButton |
| [destinationAddress](destination-address.md) | `var destinationAddress: `[`Address`](https://developer.android.com/reference/android/location/Address.html)`?`<br>represent the destination address, to get later the latitude and longitude of the chosen address. |
| [endTime](end-time.md) | `lateinit var endTime: `[`Calendar`](https://developer.android.com/reference/java/util/Calendar.html)<br>represent the end time to calculate the duration. |
| [startAddress](start-address.md) | `var startAddress: `[`Address`](https://developer.android.com/reference/android/location/Address.html)`?`<br>represent the start address, to get later the latitude and longitude of the chosen address. |
| [startFragmentTimePicker](start-fragment-time-picker.md) | `var startFragmentTimePicker: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`?`<br>This boolean is true when the onClickTimePicker() start the startTimePickerFragment(). |
| [startIntentAutoComplete](start-intent-auto-complete.md) | `var startIntentAutoComplete: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`?`<br>This boolean is true when onClickStartAddressAutoComplete() start the autocomplete Activity. To know where to set the result |
| [startTime](start-time.md) | `var startTime: `[`Calendar`](https://developer.android.com/reference/java/util/Calendar.html)<br>represent the start time to calculate the duration. |

### Functions

| Name | Summary |
|---|---|
| [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set the result of the PlaceAutocompleteActivity. When the boolean startIntentAutoComplete is true the result is set to textView_startAddress otherwise to textView_destinationAddress. |
| [onClickCategory](on-click-category.md) | `fun onClickCategory(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called when the user click on an imageButton. The imageButton who call this function become a different background colour, show a toast message and set an value to the integer category |
| [onClickDestinationAddressAutoComplete](on-click-destination-address-auto-complete.md) | `fun onClickDestinationAddressAutoComplete(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set the boolean startIntentAutoComplete to false and call the function buildPlaceAutocompleteIntent() |
| [onClickStartAddressAutoComplete](on-click-start-address-auto-complete.md) | `fun onClickStartAddressAutoComplete(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set the boolean startIntentAutoComplete to true and call the function buildPlaceAutocompleteIntent() |
| [onClickTimePicker](on-click-time-picker.md) | `fun onClickTimePicker(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set the boolean startFragmentTimePicker to true and call the function startTimePickerFragment() |
| [onClickTimePickerStart](on-click-time-picker-start.md) | `fun onClickTimePickerStart(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set the boolean startFragmentTimePicker to false and call the function startTimePickerFragment() |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This function is called when the activity is created to set the ActionBar, TimePicker and the imageButtons. |
| [onCreateOptionsMenu](on-create-options-menu.md) | `fun onCreateOptionsMenu(menu: `[`Menu`](https://developer.android.com/reference/android/view/Menu.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>set the menu on top. |
| [onOptionsItemSelected](on-options-item-selected.md) | `fun onOptionsItemSelected(item: `[`MenuItem`](https://developer.android.com/reference/android/view/MenuItem.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>call the function saveDrive() when save in the right top corner is clicked. |
| [onTimeSet](on-time-set.md) | `fun onTimeSet(view: `[`TimePicker`](https://developer.android.com/reference/android/widget/TimePicker.html)`?, hourOfDay: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, minute: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>this function set the result from the TimePickerFragment. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PLACE_AUTOCOMPLETE_REQUEST_CODE](-p-l-a-c-e_-a-u-t-o-c-o-m-p-l-e-t-e_-r-e-q-u-e-s-t_-c-o-d-e.md) | `const val PLACE_AUTOCOMPLETE_REQUEST_CODE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>to identify th placeAutocomplete request. |
