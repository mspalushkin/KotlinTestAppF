/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common.fragments

import androidx.fragment.app.Fragment
import com.palushkin.kotlintestapp.App
import com.palushkin.kotlintestapp.common.activities.BaseActivity
import com.palushkin.kotlintestapp.common.di.metapresentation.MetaPresentationModule
import com.palushkin.kotlintestapp.common.di.presentation.PresentationModule

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule(this))
    }

    private val metaPresentationComponent by lazy {
        presentationComponent.newMetaPresentationComponent(MetaPresentationModule(this))
    }

    protected val injector get() = presentationComponent
    protected val metaInjector get() = metaPresentationComponent
}