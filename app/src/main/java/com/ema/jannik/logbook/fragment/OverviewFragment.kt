package com.ema.jannik.logbook.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ema.jannik.logbook.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OverviewFragment : Fragment() {
    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * [.onCreate] and [.onActivityCreated].
     *
     *
     * If you return a View from here, you will later be called in
     * [.onDestroyView] when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_overview, container, false)

 /*       recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)  //make it more efficient

        val driveAdapter = DriveAdapter()
        recyclerView.adapter = driveAdapter

        val observer = Observer<List<Drive>> { drives: List<Drive> ->
            driveAdapter.setDrives(drives)
        }

        val viewModel: DriveViewModel by lazy {
            ViewModelProviders.of(this).get(DriveViewModel::class.java) //can pass getActivity() to chnage lifcycle
        }
        viewModel.allDrives.observe(this, observer)*/

        return view
    }

    fun onClickFabShow(view: View){
        changeVisibility(view.findViewById(R.id.fab_add), view.findViewById(R.id.textView_fab_add_description))
        changeVisibility(view.findViewById(R.id.fab_record), view.findViewById(R.id.textView_fab_record_description))
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

    fun onClickFabAdd(view: View) {
        //supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
        //    AddDriveFragment()).commit()
    }

}
