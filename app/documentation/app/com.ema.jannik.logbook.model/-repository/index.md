[app](../../index.md) / [com.ema.jannik.logbook.model](../index.md) / [Repository](./index.md)

# Repository

`class Repository`

This Repository class add an extra layer between the ViewModel and Data for abstraction purpose

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Repository(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)`<br>This Repository class add an extra layer between the ViewModel and Data for abstraction purpose |

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `fun delete(drive: `[`Drive`](../-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAll](get-all.md) | `fun getAll(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Drive`](../-drive/index.md)`>>` |
| [insert](insert.md) | `fun insert(drive: `[`Drive`](../-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>run an async task to insert an  entry into table "drive" |
