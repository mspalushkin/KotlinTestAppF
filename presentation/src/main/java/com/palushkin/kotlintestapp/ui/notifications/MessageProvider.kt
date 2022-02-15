/*
 * 13.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.notifications

import android.content.Context
import com.palushkin.kotlintestapp.R

class MessageProvider (private val context: Context) {

    fun getMessage(): String {
        return context.getString(R.string.dummy_message)
        // или так
        //return Resources.getSystem().getString(R.string.dummy_message)
    }
}