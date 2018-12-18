package com.ema.jannik.logbook.view

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.fragment.SettingFragment
import com.ema.jannik.logbook.helper.Utils
import com.ema.jannik.logbook.model.database.Drive


/**
 * This Adapter is responsible for the RecyclerView of the MainActivity
 * @property activity To access the shared preferences. This activity must be the MainActivity.
 */
class DriveAdapter(val activity: Activity) : RecyclerView.Adapter<DriveAdapter.DriveHolder>() {

    private var listener: DriveAdapter.OnItemClickListener? = null

    private var drives: List<Drive> = ArrayList<Drive>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriveHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.ride_item, parent, false)
        return DriveHolder(itemView)
    }

    override fun getItemCount(): Int {
        return drives.size
    }

    override fun onBindViewHolder(holder: DriveHolder, position: Int) {
        val currentDrive: Drive = drives.get(position)
        holder.upperRightCorner.text = getDriveProperty(
            getSettingsLayout(
                1
            ),
            currentDrive
        )
        holder.upperLeftCorner.text = getDriveProperty(
            getSettingsLayout(
                2
            ),
            currentDrive
        )
            holder.lowerLeftCorner.text = getDriveProperty(
                getSettingsLayout(
                    3
                ),
                currentDrive
            )
            holder.lowerRightCorner.text = getDriveProperty(
                getSettingsLayout(
                    4
                ),
                currentDrive
            )
        val category = Utils.getCategoryDrawableId(currentDrive.category)
        if (category != 0) {    //category == 0 -> not found
            holder.icon.setImageResource(category)
        }
    }

    fun setDrives(drives: List<Drive>) {
        this.drives = drives
        notifyDataSetChanged()

        //TODO where set no entry
    }

    inner class DriveHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: AppCompatImageView
        val upperRightCorner: TextView
        val upperLeftCorner: TextView
        val lowerLeftCorner: TextView
        val lowerRightCorner: TextView

        init {
            icon = itemView.findViewById(R.id.item_icon)
            upperRightCorner = itemView.findViewById(R.id.textView_upperRightCorner)
            upperLeftCorner = itemView.findViewById(R.id.textView_upperLeftCorner)
            lowerLeftCorner = itemView.findViewById(R.id.textView_lowerLeftCorner)
            lowerRightCorner = itemView.findViewById(R.id.textView_lowerRightCorner)

            itemView.setOnClickListener(object : View.OnClickListener {
                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                override fun onClick(v: View?) {
                    val position = getAdapterPosition()
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener!!.onItemClick(drives.get(position))
                    }

                }

            })
        }
    }

    /**
     * inteface for onItemClickListener
     */
    interface OnItemClickListener {
        fun onItemClick(drive: Drive)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun getSettingsLayout(position: Int): String {
        Log.i("SHAREDPREFERENCES", "access")
        val sharedPreferences = activity.getSharedPreferences(
            SettingFragment.SHARED_PREFERENCES, //name of shared preferences
            Context.MODE_PRIVATE                //just this application can change the preferences
        )
        Log.i("SHAREDPREFERENCES", "when")
        when (position) {
            1 -> return sharedPreferences.getString(
                SettingFragment.LAYOUT_ONE,
                ""
            )!!
            2 -> return sharedPreferences.getString(
                SettingFragment.LAYOUT_TWO,
                ""
            )!!
            3 -> return sharedPreferences.getString(
                SettingFragment.LAYOUT_THREE,
                ""
            )!!
            4 -> return sharedPreferences.getString(
                SettingFragment.LAYOUT_FOUR,
                ""
            )!!
        }
        return ""
    }

    /**
     * This function get a string, a drive object and find the suitable property in the drive class.
     * @param property use getSettingsLayout() to get this string.
     * @param drive instanced drive object
     * @return value of the suitable property. When nothing found return ""
     */
    fun getDriveProperty(property: String, drive: Drive): String {  //TODO retrurn null?
        var x = 1
        val array = activity.getResources().getStringArray(R.array.spinner_layout)
        //iterate spinner_layout array
        for(s in array){
            if(s == property)
                break
            x++
        }

        when (x) {
            1 -> return drive.purpose
            2 -> return drive.duration.toString()
            3 -> return drive.start_timestamp.toString()
            4 -> return drive.destination_timestamp.toString()
            5 -> return drive.mileageStart.toString()
            6 -> return drive.mileageDestination.toString()
            7 -> return drive.start.address
            8 -> return drive.destination.address
        }
        return ""
    }
}