[app](../../index.md) / [com.ema.jannik.logbook.model](../index.md) / [Drive](./index.md)

# Drive

`data class Drive`

Created by Jannik on 11/24/2018.
represent the table "drive" in the database.
This table contains the purpose for the drive, start, destination and the mileage at start and arrival.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Drive(purpose: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, duration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, mileageStart: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, mileageDestination: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, category: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>contains all properties except the id |

### Properties

| Name | Summary |
|---|---|
| [category](category.md) | `var category: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>represent the category: |
| [duration](duration.md) | `var duration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [id](id.md) | `var id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>represent the column dr_id, Primary Key, auto increment |
| [mileageDestination](mileage-destination.md) | `var mileageDestination: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [mileageStart](mileage-start.md) | `var mileageStart: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [purpose](purpose.md) | `var purpose: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>r |
