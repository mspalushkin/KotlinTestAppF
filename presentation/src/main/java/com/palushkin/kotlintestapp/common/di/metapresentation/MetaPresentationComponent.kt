/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common.di.metapresentation

import com.palushkin.kotlintestapp.ui.detail.DetailFragment
import dagger.Subcomponent

@MetaPresentationScope
@Subcomponent(modules = [MetaPresentationModule::class])
interface MetaPresentationComponent {

    fun inject(fragment: DetailFragment)
}