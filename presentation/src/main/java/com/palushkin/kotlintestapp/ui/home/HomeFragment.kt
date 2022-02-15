/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.palushkin.kotlintestapp.common.KeyboardInterface
import com.palushkin.kotlintestapp.common.fragments.BaseFragment
import com.palushkin.kotlintestapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.*
import java.lang.Thread.currentThread
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    @Inject
    lateinit var keyboardManager: KeyboardInterface

    @Inject
    lateinit var rxListFilter: RxListFilterInterface

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var _searchView: SearchView? = null
    private val searchView get() = _searchView!!


    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val homeFragment: HomeFragment = this
        _binding = FragmentHomeBinding.inflate(inflater)
        // non-ktx way...
        //viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        //val viewModel by viewModels<HomeViewModel> { viewModelFactory }
        val myViewModel by viewModels<HomeViewModel> { viewModelFactory }
        viewModel = myViewModel

        binding.apply {

            lifecycleOwner = homeFragment
            homeViewModel = viewModel
//            recyclerView.adapter = UserListAdapter(UserListAdapter.OnClickListener {
//                viewModel.displayUserDetails(it)
//            })

        }

        viewModel.navigateToSelectedUser.observe(
            viewLifecycleOwner
        ) {
            if (null != it) {
                keyboardManager.hideKeyboard(searchView)
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionNavigationHomeToDetailFragment(it))
                viewModel.displayUserDetailsComplete()
            }
        }

        /*(activity as MainActivity).supportActionBar?.apply {
            setDisplayShowHomeEnabled(false)
            title = null
            show()
        }
        setHasOptionsMenu(true)*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = UserListAdapter(UserListAdapter.OnClickListener {
            viewModel.displayUserDetails(it)
        })

        setToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.recyclerView.addOnAttachStateChangeListener(object :
            View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(p0: View?) {
                // no-op
            }

            override fun onViewDetachedFromWindow(p0: View?) {
                binding.recyclerView.adapter = null
            }
        })

        _searchView = null
        _binding = null
    }

    private fun setToolbar() {

        _searchView = binding.searchView
        // фильтр через rX
        // rxListFilter.setUpSearchObservable(viewModel, searchView)
        // фильтр через Flow
        viewLifecycleOwner.lifecycleScope.launch {
            rxListFilter.setUpSearchFlowable(viewModel, searchView)
        }
    }


    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.overflow_menu, menu)
        val item: MenuItem = menu.findItem(R.id.action_search)
        //val searchView: SearchView = item.actionView as SearchView
        _searchView = item.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = "Поиск"
        //searchView.clearFocus()

        rxListFilter.setUpSearchObservable(viewModel, searchView)

        super.onCreateOptionsMenu(menu, inflater)
    }*/

}