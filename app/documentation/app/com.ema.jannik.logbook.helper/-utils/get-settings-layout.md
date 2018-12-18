[app](../../index.md) / [com.ema.jannik.logbook.helper](../index.md) / [Utils](index.md) / [getSettingsLayout](./get-settings-layout.md)

# getSettingsLayout

`fun getSettingsLayout(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`

This function read the layout settings from the shared preferences and
tell you in which corner which information is displayed.

### Parameters

`application` - to access the shared references

`position` - For which corner do you want the information.
1 = upper left corner, 2 = upper right corner, 3 = lower left corner, 4 = lower right corner