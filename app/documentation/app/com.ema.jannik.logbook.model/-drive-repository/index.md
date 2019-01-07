[app](../../index.md) / [com.ema.jannik.logbook.model](../index.md) / [DriveRepository](./index.md)

# DriveRepository

`class DriveRepository`

This Repository class add an extra layer between the ViewModel and Data for abstraction purpose.
This class is used from the overview fragment to feed the DriveViewModel
Do NOT create an instace of this class on the main thread.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DriveRepository(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)`<br>This Repository class add an extra layer between the ViewModel and Data for abstraction purpose. This class is used from the overview fragment to feed the DriveViewModel Do NOT create an instace of this class on the main thread. |

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `fun delete(drive: `[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAll](get-all.md) | `fun getAll(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`>>` |
| [insert](insert.md) | `fun insert(drive: `[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>run an async task to insert an entry into table "drive" |
