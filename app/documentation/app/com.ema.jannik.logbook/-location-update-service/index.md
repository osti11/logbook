[app](../../index.md) / [com.ema.jannik.logbook](../index.md) / [LocationUpdateService](./index.md)

# LocationUpdateService

`class LocationUpdateService : `[`Service`](https://developer.android.com/reference/android/app/Service.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocationUpdateService()` |

### Properties

| Name | Summary |
|---|---|
| [ACTION_BROADCAST](-a-c-t-i-o-n_-b-r-o-a-d-c-a-s-t.md) | `val ACTION_BROADCAST: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [EXTRA_LOCATION](-e-x-t-r-a_-l-o-c-a-t-i-o-n.md) | `val EXTRA_LOCATION: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [notification](notification.md) | `var notification: `[`Notification`](https://developer.android.com/reference/android/app/Notification.html) |

### Functions

| Name | Summary |
|---|---|
| [onBind](on-bind.md) | `fun onBind(intent: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?): `[`IBinder`](https://developer.android.com/reference/android/os/IBinder.html)`?` |
| [onCreate](on-create.md) | `fun onCreate(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called by the system when the service is first created.  Do not call this method directly. |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onStartCommand](on-start-command.md) | `fun onStartCommand(intent: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
