[app](../../index.md) / [com.ema.jannik.logbook.model](../index.md) / [RecDriveRepository](./index.md)

# RecDriveRepository

`class RecDriveRepository`

this repository is used to store the data in the db after the LocationUpdateService is finish

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RecDriveRepository(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)`<br>this repository is used to store the data in the db after the LocationUpdateService is finish |

### Functions

| Name | Summary |
|---|---|
| [getAddress](get-address.md) | `fun getAddress(location: `[`Location`](https://developer.android.com/reference/android/location/Location.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>run GetAddressAsyncTask() |
| [insert](insert.md) | `fun insert(drive: `[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>run an async task to insert an entry into table "drive"`fun insert(route: `[`Route`](../../com.ema.jannik.logbook.model.database/-route/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
