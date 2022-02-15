/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.palushkin.kotlintestapp.App
import com.palushkin.kotlintestapp.common.di.activity.ActivityModule
import com.palushkin.kotlintestapp.common.di.presentation.PresentationModule


open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as App).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule(this))
    }

    protected val injector get() = presentationComponent
}