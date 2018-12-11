[app](../../index.md) / [com.ema.jannik.logbook](../index.md) / [LocationUpdateService](./index.md)

# LocationUpdateService

`class LocationUpdateService : `[`Service`](https://developer.android.com/reference/android/app/Service.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocationUpdateService()` |

### Functions

| Name | Summary |
|---|---|
| [onBind](on-bind.md) | `fun onBind(intent: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?): `[`IBinder`](https://developer.android.com/reference/android/os/IBinder.html)`?` |
| [onCreate](on-create.md) | `fun onCreate(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called by the system when the service is first created.  Do not call this method directly. |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onStartCommand](on-start-command.md) | `fun onStartCommand(intent: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
