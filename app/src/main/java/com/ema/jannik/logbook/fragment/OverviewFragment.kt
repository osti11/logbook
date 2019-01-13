package com.ema.jannik.logbook.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.view.DriveAdapter
import com.ema.jannik.logbook.viewmodel.DriveViewModel
import kotlinx.android.synthetic.main.fragment_overview.*
import com.ema.jannik.logbook.activity.DetailsDriveActivity
import com.ema.jannik.logbook.model.AddDriveRepository
import com.ema.jannik.logbook.model.DriveRepository
import com.ema.jannik.logbook.model.database.Stage
import com.google.android.material.snackbar.Snackbar
import java.util.*

class OverviewFragment : Fragment() {

    val TAG = this::class.java.toString()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //--set recyclerView--
        Log.i(TAG, "layoutManager")
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)  //make it more efficient
        Log.i(TAG, "init Drive Adapter")
        val driveAdapter = DriveAdapter(activity!!)
        Log.i(TAG, "set DriveAdapter")
        recyclerView.adapter = driveAdapter

        Log.i(TAG, "Observe")
        //--set observer for LiveData--
        val observer = Observer<List<Drive>> { drives: List<Drive> ->
            run {
                driveAdapter.setDrives(drives)
                if (driveAdapter.itemCount == 0) {  //set textView when now entry          //TODO hier richtig?
                    textView_noEntry.visibility = View.VISIBLE
                } else {
                    textView_noEntry.visibility = View.GONE
                }
            }

        }

        //--set
        driveAdapter.setOnItemClickListener(object : DriveAdapter.OnItemClickListener {
            /**
             * Called when a item has been clicked.
             * @param drive The item that was clicked.
             */
            override fun onItemClick(drive: Drive) {
                if (drive.start != null && drive.destination != null){
                    val intent = Intent(context, DetailsDriveActivity::class.java)
                    intent.putExtra(DetailsDriveActivity.EXTRA_ID, drive.id)
                    startActivity(intent)
                }
            }
        })

        //--set observer to ViewModel--
        val viewModel: DriveViewModel by lazy {
            ViewModelProviders.of(this).get(DriveViewModel::class.java) //can pass getActivity() to chnage lifcycle
        }
        viewModel.allDrives.observe(viewLifecycleOwner, observer)

        //--set swipe to delete--
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) { //swipe direction to call onSwiped()
            /**
             * Delete the element by swipe
             */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val drive = driveAdapter.getDriveAt(viewHolder.adapterPosition)
                viewModel.delete(drive) //delete
                if(getDeleteSettings()){    //updateed newer entries when in settigns
                    updateNewerDrives(drive, false)
                }

                //show snackbar
                val snackbar = Snackbar.make(view!!, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                    .setAction(R.string.snackbar_button) {
                        viewModel.insert(drive = drive) //when click UNDO insert the deleted entry again
                        if(getDeleteSettings()) //when in settings
                            updateNewerDrives(drive, true)
                    }
                snackbar.show()


            }

            /**
             * For drag and drop, which is not used here
             */
            override fun onMove(    //this is for drag and drop, which is not used here
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
        }).attachToRecyclerView(recyclerView)
    }

    /**
     * Passt den Kilometerstand der neueren Einträge an die Entfernung des gelöschten Eintrags an
     * @param drive Eintrag der aud der Db gelöscht wurde.
     * @param todo TRUE = Löschen rückgängig machen, FALSE = KIlometerstand an gelöschenten Eintrag anpassen
     */
    private fun updateNewerDrives(drive: Drive, todo: Boolean) {
        val repository = DriveRepository(activity!!.application)
        //Holt sich alle Einträge deren StartZeitpunkt > gelöschenterEintrag.destination_timestamp
        val drives = repository.getAllAfter(drive.destination_timestamp)

        if(todo){
            //addiert Entfernung des gelöschten Eintrags vom Kilometerstand ab und aktuallisiert DB
            drives.forEach {
                it.mileageStart += drive.distance
                it.mileageDestination += drive.distance
                repository.update(it)
            }
        } else {

            //zieht Entfernung des gelöschten Eintrags vom Kilometerstand ab und aktuallisiert DB
            drives.forEach {
                it.mileageStart -= drive.distance
                it.mileageDestination -= drive.distance
                repository.update(it)
            }
        }

    }

    /**
     * get DELETE_MODIFY setting from share preferences. If true modify the mileage of the newer db entries.
     * @return DELETE_MODIFY setting
     */
    private fun getDeleteSettings(): Boolean {
        val sharedPreferences = activity!!.getSharedPreferences(
            SettingFragment.SHARED_PREFERENCES, //name of shared preferences
            Context.MODE_PRIVATE        //just this application can change the preferences
        )

        Log.i(TAG, "getDeleteSettings() " + sharedPreferences.getBoolean(
            SettingFragment.DELETE_MODIFY,
            false
        ).toString())

        return sharedPreferences.getBoolean(
            SettingFragment.DELETE_MODIFY,
            false
        )
    }
}
