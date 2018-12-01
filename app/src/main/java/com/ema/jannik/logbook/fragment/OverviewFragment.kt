package com.ema.jannik.logbook.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val observer = Observer<List<Drive>> { drives: List<Drive> ->
            driveAdapter.setDrives(drives)
        }

        //--set observer to ViewModel--
        val viewModel: DriveViewModel by lazy {
            ViewModelProviders.of(this).get(DriveViewModel::class.java) //can pass getActivity() to chnage lifcycle
        }
        viewModel.allDrives.observe(viewLifecycleOwner, observer)
    }
}
