[app](../../index.md) / [com.ema.jannik.logbook.fragment](../index.md) / [SettingFragment](index.md) / [onItemSelected](./on-item-selected.md)

# onItemSelected

`fun onItemSelected(parent: `[`AdapterView`](https://developer.android.com/reference/android/widget/AdapterView.html)`<*>?, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, id: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Callback method to be invoked when an item in this view has been selected. This callback is invoked only when
the newly selected position is different from the previously selected position or if there was no selected item.

### Parameters

`parent` - The AdapterView where the selection happened

`view` - The view within the AdapterView that was clicked

`position` - The position of the view in the adapter

`id` - The row id of the item that is selected