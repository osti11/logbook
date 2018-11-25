[app](../index.md) / [com.ema.jannik.logbook.model](./index.md)

## Package com.ema.jannik.logbook.model

### Types

| Name | Summary |
|---|---|
| [AppDatabase](-app-database/index.md) | `abstract class AppDatabase : RoomDatabase`<br>This abstract class represent the Database which contains the tables drive, route and stage. |
| [Drive](-drive/index.md) | `data class Drive`<br>Created by Jannik on 11/24/2018. represent the table "drive" in the database. This table contains the purpose for the drive, start, destination and the mileage at start and arrival. |
| [DriveDao](-drive-dao/index.md) | `interface DriveDao`<br>This interface contains queries and other operations on the table drive. |
| [Repository](-repository/index.md) | `class Repository`<br>This Repository class add an extra layer between the ViewModel and Data for abstraction purpose |
| [Route](-route/index.md) | `data class Route`<br>Created by Jannik on 11/24/2018. This class represent the table route and contains geo locations. These are used for Polylinies to show the route on maps. This table has no entry when the journey is added manuel. |
| [RouteDao](-route-dao/index.md) | `interface RouteDao`<br>This interface contains queries and other operations on the table route. |
| [Stage](-stage/index.md) | `data class Stage`<br>This class represent the table stage and contains an geo position and the address. Which is used to save the start and destination of a journey. |
| [StageDao](-stage-dao/index.md) | `interface StageDao`<br>This interface contains queries and other operations on the table stage. |
