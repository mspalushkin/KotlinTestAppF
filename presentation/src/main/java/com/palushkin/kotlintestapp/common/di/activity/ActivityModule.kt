/*
 * 02.06.2021
 * @author Maksim Palushkin
 */


package com.palushkin.kotlintestapp.common.di.activity

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    val activity: AppCompatActivity
    ) {

    @Provides
    fun activity() = activity
}