[app](../../index.md) / [com.ema.jannik.logbook](../index.md) / [LocationActivity](./index.md)

# LocationActivity

`class LocationActivity : AppCompatActivity`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocationActivity()` |

### Properties

| Name | Summary |
|---|---|
| [fusedLocationProviderClient](fused-location-provider-client.md) | `lateinit var fusedLocationProviderClient: FusedLocationProviderClient` |
| [locationCallback](location-callback.md) | `lateinit var locationCallback: LocationCallback` |
| [locationRequest](location-request.md) | `lateinit var locationRequest: LocationRequest` |

### Functions

| Name | Summary |
|---|---|
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onRequestPermissionsResult](on-request-permissions-result.md) | `fun onRequestPermissionsResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, permissions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, grantResults: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [REQUEST_CODE](-r-e-q-u-e-s-t_-c-o-d-e.md) | `const val REQUEST_CODE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
