[app](../../index.md) / [com.ema.jannik.logbook.model](../index.md) / [AppDatabase](./index.md)

# AppDatabase

`abstract class AppDatabase : RoomDatabase`

Created by Jannik on 11/24/2018.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AppDatabase()`<br>Created by Jannik on 11/24/2018. |

### Functions

| Name | Summary |
|---|---|
| [driveDao](drive-dao.md) | `abstract fun driveDao(): `[`DriveDao`](../-drive-dao/index.md) |
| [routeDao](route-dao.md) | `abstract fun routeDao(): `[`RouteDao`](../-route-dao/index.md) |
| [stageDao](stage-dao.md) | `abstract fun stageDao(): `[`StageDao`](../-stage-dao/index.md) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [destroyInstance](destroy-instance.md) | `fun destroyInstance(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set the RoomDatabase instance to null |
| [getInstance](get-instance.md) | `fun getInstance(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`AppDatabase`](./index.md)`?`<br>create an instance of the RoomDatabase if the instance is null |
