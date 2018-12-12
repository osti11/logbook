package com.ema.jannik.logbook.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.model.database.Drive
import android.provider.ContactsContract.CommonDataKinds.Note
import com.ema.jannik.logbook.Utils


/**
 * This Adapter is responsible for the RecyclerView of the MainActivity
 */
class DriveAdapter : RecyclerView.Adapter<DriveAdapter.DriveHolder>() {

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
        holder.distance.setText(currentDrive.mileageStart.toString())   //TODO aktualisiern db
        holder.purpose.setText(currentDrive.start.address)    //TODO variabel gestalten
        holder.date.setText(currentDrive.start_timestamp.toString()) //TODO aktualisiern db
        holder.duration.setText(currentDrive.duration.toString())

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

            itemView.setOnClickListener(object : View.OnClickListener {
                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                override fun onClick(v: View?) {
                    val position = getAdapterPosition ()
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
    }