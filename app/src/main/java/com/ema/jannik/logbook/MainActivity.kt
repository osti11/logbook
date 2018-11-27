package com.ema.jannik.logbook

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.view.DriveAdapter
import com.ema.jannik.logbook.viewmodel.DriveViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)  //make it more efficient

        val driveAdapter: DriveAdapter = DriveAdapter()
        recyclerView.adapter = driveAdapter

        val observer = Observer<List<Drive>> { drives: List<Drive> ->
            driveAdapter.setDrives(drives)
        }

        val viewModel: DriveViewModel by lazy {
            ViewModelProviders.of(this).get(DriveViewModel::class.java)
        }
        viewModel.allDrives.observe(this, observer)
    }

    fun onClickFabShow(view: View) {
        changeVisibility(fab_add, textView_fab_add_description)
        changeVisibility(fab_record, textView_fab_record_description)
    }

    fun changeVisibility(floatingActionButton: FloatingActionButton, textView: TextView) {
        if (floatingActionButton.visibility == View.VISIBLE) {
            floatingActionButton.hide()
            textView.visibility = View.GONE
        } else if (floatingActionButton.visibility == View.GONE) {
            floatingActionButton.show()
            textView.visibility = View.VISIBLE
        }
    }
}
