[app](../../index.md) / [com.ema.jannik.logbook.viewmodel](../index.md) / [DriveViewModel](./index.md)

# DriveViewModel

`class DriveViewModel : AndroidViewModel`

This VieModel provides the data for the View of the MainActivity.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DriveViewModel(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)`<br>This VieModel provides the data for the View of the MainActivity. |

### Properties

| Name | Summary |
|---|---|
| [allDrives](all-drives.md) | `val allDrives: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `fun delete(drive: `[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAll](get-all.md) | `fun getAll(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`>>` |
| [insert](insert.md) | `fun insert(drive: `[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
