/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common.di.presentation

import com.palushkin.kotlintestapp.common.di.metapresentation.MetaPresentationComponent
import com.palushkin.kotlintestapp.common.di.metapresentation.MetaPresentationModule
import com.palushkin.kotlintestapp.ui.home.HomeFragment
import com.palushkin.kotlintestapp.ui.notifications.NotificationsFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: NotificationsFragment)
    fun newMetaPresentationComponent(metaPresentationModule: MetaPresentationModule): MetaPresentationComponent
}