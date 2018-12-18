[app](../../index.md) / [com.ema.jannik.logbook.view](../index.md) / [DriveAdapter](./index.md)

# DriveAdapter

`class DriveAdapter : Adapter<`[`DriveHolder`](-drive-holder/index.md)`>`

This Adapter is responsible for the RecyclerView of the MainActivity

### Types

| Name | Summary |
|---|---|
| [DriveHolder](-drive-holder/index.md) | `inner class DriveHolder : ViewHolder` |
| [OnItemClickListener](-on-item-click-listener/index.md) | `interface OnItemClickListener`<br>inteface for onItemClickListener |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DriveAdapter(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`)`<br>This Adapter is responsible for the RecyclerView of the MainActivity |

### Properties

| Name | Summary |
|---|---|
| [activity](activity.md) | `val activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)<br>To access the shared preferences. This activity must be the MainActivity. |

### Functions

| Name | Summary |
|---|---|
| [getDriveProperty](get-drive-property.md) | `fun getDriveProperty(property: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, drive: `[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function get a string, a drive object and find the suitable property in the drive class. |
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getSettingsLayout](get-settings-layout.md) | `fun getSettingsLayout(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`DriveHolder`](-drive-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DriveHolder`](-drive-holder/index.md) |
| [setDrives](set-drives.md) | `fun setDrives(drives: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setOnItemClickListener](set-on-item-click-listener.md) | `fun setOnItemClickListener(listener: `[`OnItemClickListener`](-on-item-click-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
