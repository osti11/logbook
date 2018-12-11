[app](../../index.md) / [com.ema.jannik.logbook.model.location](../index.md) / [LocationUpdates](./index.md)

# LocationUpdates

`class LocationUpdates`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocationUpdates(application: `[`Application`](https://developer.android.com/reference/android/app/Application.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [application](application.md) | `val application: `[`Application`](https://developer.android.com/reference/android/app/Application.html) |

### Functions

| Name | Summary |
|---|---|
| [startLocationUpdate](start-location-update.md) | `fun startLocationUpdate(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [stopLocationUpdates](stop-location-updates.md) | `fun stopLocationUpdates(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Location`](https://developer.android.com/reference/android/location/Location.html)`>`<br>This function stop the location updates and return a list with all locations since the requestLocationUpdates() was call. |
