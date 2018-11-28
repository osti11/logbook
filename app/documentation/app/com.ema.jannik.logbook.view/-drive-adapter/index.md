[app](../../index.md) / [com.ema.jannik.logbook.view](../index.md) / [DriveAdapter](./index.md)

# DriveAdapter

`class DriveAdapter : Adapter<`[`DriveHolder`](-drive-holder/index.md)`>`

This Adapter is responsible for the RecyclerView of the MainActivity

### Types

| Name | Summary |
|---|---|
| [DriveHolder](-drive-holder/index.md) | `inner class DriveHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DriveAdapter()`<br>This Adapter is responsible for the RecyclerView of the MainActivity |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`DriveHolder`](-drive-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DriveHolder`](-drive-holder/index.md) |
| [setDrives](set-drives.md) | `fun setDrives(drives: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Drive`](../../com.ema.jannik.logbook.model.database/-drive/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
