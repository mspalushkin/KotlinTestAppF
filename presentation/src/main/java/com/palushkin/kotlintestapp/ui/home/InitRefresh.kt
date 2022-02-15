/*
 * 01.08.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import com.palushkin.kotlintestapp.App

class InitRefresh(private val context: Context) : InitInterface {

    override fun refreshOk() {
        (context.applicationContext as App).succeedDataRefresh()
    }

    override fun refreshCheck(): Boolean {
        return (context.applicationContext as App).checkDataRefresh()
    }
}

interface InitInterface {
    fun refreshOk()
    fun refreshCheck(): Boolean
}

@SuppressLint("StaticFieldLeak")
private lateinit var INSTANCERefresh: InitRefresh

fun getInitRefresh(context: Context): InitRefresh {
    synchronized(InitRefresh::class.java) {
        if (!::INSTANCERefresh.isInitialized) {
            INSTANCERefresh = InitRefresh(
                context.applicationContext
            )
        }
    }
    return INSTANCERefresh
}