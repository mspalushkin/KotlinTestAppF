/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.palushkin.kotlintestapp.MainActivity
import com.palushkin.kotlintestapp.R
import com.palushkin.kotlintestapp.common.fragments.BaseFragment
import javax.inject.Inject

class NotificationsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: NotificationsViewModelFactory

    //private lateinit var viewModel: NotificationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // non-ktx way...
        //viewModel = ViewModelProvider(this, viewModelFactory).get(NotificationsViewModel::class.java)
        val viewModel by viewModels<NotificationsViewModel>{ viewModelFactory }

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //(activity as MainActivity).supportActionBar?.hide()

        return root
    }
}