[app](../../index.md) / [com.ema.jannik.logbook.helper](../index.md) / [Utils](./index.md)

# Utils

`class Utils`

This class contains functions which are used over the project

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Utils()`<br>This class contains functions which are used over the project |

### Companion Object Functions

| Name | Summary |
|---|---|
| [getCategory](get-category.md) | `fun getCategory(category: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>This function recieve the category as Int and return an description |
| [getCategoryDrawableId](get-category-drawable-id.md) | `fun getCategoryDrawableId(category: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getDriveProperty](get-drive-property.md) | `fun getDriveProperty(property: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, drive: `[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function get a string, a drive object and find the suitable property in the drive class. |
| [getSettingsLayout](get-settings-layout.md) | `fun getSettingsLayout(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>This function read the layout settings from the shared preferences and tell you in which corner which information is displayed. |
