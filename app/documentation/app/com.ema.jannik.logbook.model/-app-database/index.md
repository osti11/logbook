[app](../../index.md) / [com.ema.jannik.logbook.model](../index.md) / [AppDatabase](./index.md)

# AppDatabase

`abstract class AppDatabase : RoomDatabase`

This abstract class represent the Database which contains the tables drive, route and stage.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AppDatabase()`<br>This abstract class represent the Database which contains the tables drive, route and stage. |

### Functions

| Name | Summary |
|---|---|
| [driveDao](drive-dao.md) | `abstract fun driveDao(): `[`DriveDao`](../-drive-dao/index.md) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [destroyInstance](destroy-instance.md) | `fun destroyInstance(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set the RoomDatabase instance to null |
| [getInstance](get-instance.md) | `fun getInstance(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`AppDatabase`](./index.md)`?`<br>Singleton Pattern, because each RoomDatabase instance is fairly expensive. Create an instance of the RoomDatabase if the instance is null |
