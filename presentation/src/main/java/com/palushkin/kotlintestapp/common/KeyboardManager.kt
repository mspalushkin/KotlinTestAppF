/*
 * 04.08.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common

import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardManager(private val context: Context) : KeyboardInterface {

    override fun hideKeyboard(view: View){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            //view.clearFocus()
            // Hide the keyboard.
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

interface KeyboardInterface {
    fun hideKeyboard(view: View)
}