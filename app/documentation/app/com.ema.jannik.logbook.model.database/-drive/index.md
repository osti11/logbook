[app](../../index.md) / [com.ema.jannik.logbook.model.database](../index.md) / [Drive](./index.md)

# Drive

`data class Drive`

Created by Jannik on 11/24/2018.
represent the table "drive" in the database.
This table contains the purpose for the drive, start, destination and the mileage at start and arrival.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Drive(purpose: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, duration: `[`Date`](https://developer.android.com/reference/java/sql/Date.html)`, start_timestamp: `[`Date`](https://developer.android.com/reference/java/sql/Date.html)`, destination_timestamp: `[`Date`](https://developer.android.com/reference/java/sql/Date.html)`, mileageStart: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, mileageDestination: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, category: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, start: `[`Stage`](../-stage/index.md)`, destination: `[`Stage`](../-stage/index.md)`)`<br>contains all properties except the id |

### Properties

| Name | Summary |
|---|---|
| [category](category.md) | `var category: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>represent the category. Values: 0 = uncategorized, 1 = private, 2 = work, 3 = way to work/home |
| [destination](destination.md) | `var destination: `[`Stage`](../-stage/index.md)<br>represent the column dr_destination, contains the Location at the end of the journey |
| [destination_timestamp](destination_timestamp.md) | `var destination_timestamp: `[`Date`](https://developer.android.com/reference/java/sql/Date.html) |
| [duration](duration.md) | `var duration: `[`Date`](https://developer.android.com/reference/java/sql/Date.html)<br>represent the column dr_duration ehich contains the duration of the journey |
| [id](id.md) | `var id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>represent the column dr_id, Primary Key, auto increment |
| [mileageDestination](mileage-destination.md) | `var mileageDestination: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>represent the column dr_mileage_destination, mileage of the car at the end of the journey |
| [mileageStart](mileage-start.md) | `var mileageStart: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>represent the column dr_mileage_start, mileage of the car at the start of the journey |
| [purpose](purpose.md) | `var purpose: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>represent the column dr_purpose, purpose of the drive |
| [start](start.md) | `var start: `[`Stage`](../-stage/index.md)<br>represent the column dr_start, contains the Location at the start of the journey |
| [start_timestamp](start_timestamp.md) | `var start_timestamp: `[`Date`](https://developer.android.com/reference/java/sql/Date.html) |
