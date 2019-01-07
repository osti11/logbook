[app](../../index.md) / [com.ema.jannik.logbook.model.database](../index.md) / [Converts](./index.md)

# Converts

`class Converts`

This class converts unstorable data types into data types that can be stored in the database

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Converts()`<br>This class converts unstorable data types into data types that can be stored in the database |

### Companion Object Functions

| Name | Summary |
|---|---|
| [dateToTimestamp](date-to-timestamp.md) | `fun dateToTimestamp(date: `[`Date`](https://developer.android.com/reference/java/sql/Date.html)`?): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?`<br>convert Date back into timestamp |
| [fromTimestamp](from-timestamp.md) | `fun fromTimestamp(value: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`?): `[`Date`](https://developer.android.com/reference/java/sql/Date.html)`?`<br>convert Timestamp into Date, to save it in the database |
