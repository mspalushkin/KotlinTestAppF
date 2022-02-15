/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.palushkin.kotlintestapp.MainActivity
import com.palushkin.kotlintestapp.common.fragments.BaseFragment
import com.palushkin.kotlintestapp.databinding.FragmentDetailBinding
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        metaInjector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val detailFragment: DetailFragment = this
        _binding = FragmentDetailBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.apply {

            lifecycleOwner = detailFragment
            detailViewModel = viewModel
        }

        //(activity as MainActivity).supportActionBar?.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setToolbar() {

        binding.myToolbar.setNavigationOnClickListener { v ->
            v.findNavController().navigateUp()
        }

    }
}