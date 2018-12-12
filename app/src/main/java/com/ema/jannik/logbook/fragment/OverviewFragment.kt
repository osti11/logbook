package com.ema.jannik.logbook.fragment

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.view.DriveAdapter
import com.ema.jannik.logbook.viewmodel.DriveViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_overview.*
import android.view.Menu
import android.view.MenuItem
import com.ema.jannik.logbook.DetailsDriveActivity
import com.ema.jannik.logbook.MainActivity
import com.ema.jannik.logbook.model.DriveRepository
import com.ema.jannik.logbook.model.database.Stage
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp
import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.zip.DataFormatException

class OverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //--set recyclerView--
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)  //make it more efficient
        val driveAdapter = DriveAdapter()
        recyclerView.adapter = driveAdapter

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
        driveAdapter.setOnItemClickListener(object :DriveAdapter.OnItemClickListener{
            /**
             * Called when a item has been clicked.
             * @param drive The item that was clicked.
             */
            override fun onItemClick(drive: Drive) {
                val intent = Intent(context, DetailsDriveActivity::class.java)
                intent.putExtra(DetailsDriveActivity.EXTRA__ID, drive.id)
                startActivity(intent)
            }
        })

        //TODO test data
        val start = Stage(
            address = "Rödersheim Wachenheimer Straße 10",
            latitude = 49.4300581,
            longitude = 8.2462472
        )
        val end = Stage(
            address = "Adolf Schuch",
            latitude = 49.6196093,
            longitude = 8.0845617
        )
        val drive = Drive(
            purpose = "Fahrt zur Arbeit",
            start = start,
            destination = end,
            start_timestamp = Date(22222),
            destination_timestamp = Date(2233),
            duration = Date(3),
            mileageStart = 20000.0,
            mileageDestination = 20040.0,
            category = 3
        )

        val driveRepository = DriveRepository(activity!!.application)
        driveRepository.insert(drive)

        //--set observer to ViewModel--
        val viewModel: DriveViewModel by lazy {
            ViewModelProviders.of(this).get(DriveViewModel::class.java) //can pass getActivity() to chnage lifcycle
        }
        viewModel.allDrives.observe(viewLifecycleOwner, observer)
    }
}
