package com.ema.jannik.logbook.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.model.database.Drive

/**
 * This Adapter is responsible for the RecyclerView of the MainActivity
 */
class DriveAdapter : RecyclerView.Adapter<DriveAdapter.DriveHolder>() {
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
        holder.distance.setText(currentDrive.mileageStart.toString())   //TODO aktualisiern db
        holder.purpose.setText(currentDrive.purpose)
        holder.date.setText(currentDrive.start_timestamp.toString()) //TODO aktualisiern db
        holder.duration.setText(currentDrive.duration.toString())
        if(currentDrive.category == 0)
            holder.icon.setImageResource(R.drawable.ic_directions_car_black_24dp)
        else if (currentDrive.category == 1)
            holder.icon.setImageResource(R.drawable.ic_work_grey_24dp)
        else if (currentDrive.category == 2)
            holder.icon.setImageResource(R.drawable.ic_private_grey_24dp)
        else if (currentDrive.category == 3)    //Weg zur Arbeit
            holder.icon.setImageResource(R.drawable.ic_home_black_24dp)
        else if (currentDrive.category == 4)    //Weg zur Arbeit
            holder.icon.setImageResource(R.drawable.ic_home_location)
    }

    fun setDrives(drives: List<Drive>){
        this.drives = drives
        notifyDataSetChanged()
    }

    inner class DriveHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: AppCompatImageView
        val distance: TextView
        val purpose: TextView
        val date: TextView
        val duration: TextView

        init {
            icon = itemView.findViewById(R.id.item_icon)
            distance = itemView.findViewById(R.id.text_view_distance)
            purpose = itemView.findViewById(R.id.a)
            date = itemView.findViewById(R.id.date)
            duration = itemView.findViewById(R.id.duration)
        }
    }
}