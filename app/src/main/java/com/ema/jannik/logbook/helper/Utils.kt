package com.ema.jannik.logbook.helper

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.fragment.SettingFragment
import com.ema.jannik.logbook.model.database.Drive

/**
 * This class contains functions which are used over the project
 */
class Utils {
    companion object {
        /**
         * This function recieve the category as Int and return an description
         * @param category category as int
         * @return integer from the string.xml with the description
         */
        fun getCategory(category: Int): Int {
            when (category) {
                0 -> {
                    return R.string.category_0
                }
                1 -> {
                    return R.string.category_1
                }
                2 -> {
                    return R.string.category_2
                }
                3 -> {
                    return R.string.category_3
                }
                4 -> {
                    return R.string.category_4
                }
            }
            return 0
        }

        /**
         * @param category the category
         * @return return id of the drawable or 0 when not found
         */
        fun getCategoryDrawableId(category: Int): Int {
            when (category) {
                0 -> {
                    return R.drawable.ic_car
                }
                1 -> {
                    return R.drawable.ic_private
                }
                2 -> {
                    return R.drawable.ic_work
                }
                3 -> {
                    return R.drawable.ic_home_location
                }
            }
            return 0
        }

        /**
         * This function get an int value und pass the imageButton which represent this value.
         * @param category the category from an drive object.
         * @return return id of the ImageButton.
         */
        fun getImageButtonByCategory(category: Int): Int {
            when (category) {
                0 -> {
                    return R.id.imageButton_noCategory
                }
                1 -> {
                    return R.id.imageButton_private
                }
                2 -> {
                    return R.id.imageButton_work
                }
                3 -> {
                    return R.id.imageButton_way
                }
            }
            return 0
        }

        /**
         * This function read the layout settings from the shared preferences and
         * tell you in which corner which information is displayed.
         * @param application to access the shared references
         * @param position For which corner do you want the information.
         * 1 = upper left corner, 2 = upper right corner, 3 = lower left corner, 4 = lower right corner
         */
        fun getSettingsLayout(application: Application, position: Int): String? {
            val sharedPreferences = application.getSharedPreferences(
                SettingFragment.SHARED_PREFERENCES, //name of shared preferences
                Context.MODE_PRIVATE                //just this application can change the preferences
            )

            when (position) {
                1 -> return sharedPreferences.getString(
                    SettingFragment.LAYOUT_ONE,
                    null
                )
                2 -> return sharedPreferences.getString(
                    SettingFragment.LAYOUT_TWO,
                    null
                )
                3 -> return sharedPreferences.getString(
                    SettingFragment.LAYOUT_THREE,
                    null
                )
                4 -> return sharedPreferences.getString(
                    SettingFragment.LAYOUT_FOUR,
                    null
                )
            }
            return null
        }

        /**
         * This function get a string, a drive object and find the suitable property in the drive class.
         * @param property use getSettingsLayout() to get this string.
         * @param drive instanced drive object
         * @return value of the suitable property. When nothing found return ""
         */
        fun getDriveProperty(property: String, drive: Drive): String {
            var x = 1
            val array = Resources.getSystem().getStringArray(R.array.spinner_layout)
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
                7 -> return drive.start!!.address
                8 -> return drive.destination!!.address
            }
            return ""
        }
    }
}