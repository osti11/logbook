[app](../../index.md) / [com.ema.jannik.logbook.model](../index.md) / [Route](./index.md)

# Route

`data class Route`

Created by Jannik on 11/24/2018.
This class represent the table route and contains geo locations.
These are used for Polylinies to show the route on maps.
This table has no entry when the journey is added manuel.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Route(driveId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, latitude: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, longitude: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`)`<br>contains all properties except the id |

### Properties

| Name | Summary |
|---|---|
| [driveId](drive-id.md) | `var driveId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>reference the column ro_drid which is a foreign key to drive.dr_id |
| [id](id.md) | `var id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>represent the column ro_id, primary key, auto generated |
| [latitude](latitude.md) | `var latitude: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>represent the column ro_latitude which contains a latitude to represent the location |
| [longitude](longitude.md) | `var longitude: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>represent the column ro_latitude which contains a longitude to represent the location |
