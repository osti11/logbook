package com.ema.jannik.logbook

import android.content.res.Resources

/**
 * This class contains functions which are used over the project
 */
class Utils {
        companion object {
            /**
             * This function recieve the category as Int and return an description
             * @param category category as int
             * @return integer from the string.xml with the description //TODO überarbeiten
             */
            fun getCategory(category: Int): Int{
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
                }
                return 0
            }

            /**
             * @param category the category
             * @return return id of the drawable or 0 when not found
             */
            fun getCategoryDrawableId(category: Int): Int{    //TODO drawable
                when (category){
                    0 -> {
                        return R.drawable.ic_directions_car_black_24dp
                    }
                    1 -> {
                        return R.drawable.ic_private_grey_24dp
                    }
                    2 -> {
                        return R.drawable.ic_work_grey_24dp
                    }
                    3 -> {
                        return R.drawable.ic_home_location
                    }
                }
                return 0
            }
        }


}