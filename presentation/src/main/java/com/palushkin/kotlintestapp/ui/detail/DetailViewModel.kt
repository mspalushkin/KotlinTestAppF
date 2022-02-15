/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.detail

import androidx.lifecycle.*
import com.palushkin.kotlintestapp.common.DomainUser
import javax.inject.Inject
import javax.inject.Provider

class DetailViewModel @Inject constructor(
    fragArgs: FragArgs
    ) : ViewModel() {

    private val _selectedUser = MutableLiveData<DomainUser>()

    val selectedUser: LiveData<DomainUser>
        get() = _selectedUser

    init {
        _selectedUser.value = fragArgs.getDetailFragmentArgs()
    }
}


class DetailViewModelFactory @Inject constructor(
    private val myViewModelProvider: Provider<DetailViewModel>
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return myViewModelProvider.get() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}