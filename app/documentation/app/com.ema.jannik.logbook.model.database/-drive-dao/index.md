[app](../../index.md) / [com.ema.jannik.logbook.model.database](../index.md) / [DriveDao](./index.md)

# DriveDao

`interface DriveDao`

This interface contains queries and other operations on the table drive.

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(drive: `[`Drive`](../-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>delete an entry in the table drive |
| [getAll](get-all.md) | `abstract fun getAll(): LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Drive`](../-drive/index.md)`>>`<br>Query: "SELECT * FROM drive" Get all entries from the table drive. |
| [getById](get-by-id.md) | `abstract fun getById(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Drive`](../-drive/index.md)<br>Query: SELECT * FROM drive where dr_id = id Get entry by id. |
| [insert](insert.md) | `abstract fun insert(drive: `[`Drive`](../-drive/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>insert an entry in the table drive |
