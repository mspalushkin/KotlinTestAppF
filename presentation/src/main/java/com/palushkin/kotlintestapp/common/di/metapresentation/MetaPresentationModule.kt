/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common.di.metapresentation

import com.palushkin.kotlintestapp.common.fragments.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class MetaPresentationModule(
    private val fragment: BaseFragment
) {

    @Provides
    fun fragment() = fragment
}