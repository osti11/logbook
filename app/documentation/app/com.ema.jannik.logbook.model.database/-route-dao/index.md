[app](../../index.md) / [com.ema.jannik.logbook.model.database](../index.md) / [RouteDao](./index.md)

# RouteDao

`interface RouteDao`

This interface contains queries and other operations on the table route.

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(route: `[`Route`](../-route/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getByDrId](get-by-dr-id.md) | `abstract fun getByDrId(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Route`](../-route/index.md)<br>Get rout by FOREIGN KEY ro_drid |
| [insert](insert.md) | `abstract fun insert(route: `[`Route`](../-route/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
