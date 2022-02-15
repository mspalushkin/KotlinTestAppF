/*
 * 06.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.detail

import com.palushkin.kotlintestapp.common.DomainUser
import com.palushkin.kotlintestapp.common.fragments.BaseFragment
import javax.inject.Inject

class FragArgs @Inject constructor(private val baseFragment: BaseFragment) {

    fun getDetailFragmentArgs(): DomainUser {

        return DetailFragmentArgs.fromBundle(baseFragment.requireArguments()).selectedUser
    }
}