/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.home

import androidx.lifecycle.*
import com.palushkin.data.database.repository.Repository
import com.palushkin.domain.service.ProjectListUseCases
import com.palushkin.kotlintestapp.common.DomainUser
import com.palushkin.kotlintestapp.common.asMVModel
import com.palushkin.kotlintestapp.common.deepCopy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

enum class UserApiStatus { LOADING, ERROR, DONE }

class HomeViewModel @Inject constructor(
    private val projectListUseCases: ProjectListUseCases,
    repository: Repository,
    private val initRefresh: InitInterface,
) : ViewModel() {

    //private val uiScope = viewModelScope
    //private var viewModelJob = Job()
    //private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _status = MutableLiveData<UserApiStatus>()
    val status: LiveData<UserApiStatus>
        get() = _status

    private val _navigateToSelectedUser = MutableLiveData<DomainUser>()
    val navigateToSelectedUser: LiveData<DomainUser>
        get() = _navigateToSelectedUser

    init {
        // однократный запрос данных с сервера
        initDataRefresh()
    }

    private val _properties: LiveData<List<DomainUser>> =
        Transformations.map(repository.domainUsers) {
            it.asMVModel()
        }

    // Flow into LiveData
    private val _propertiesF: LiveData<List<DomainUser>> =
        repository.domainUsersFlow
            .map { value -> value.asMVModel() }
            .asLiveData()

    val properties: LiveData<List<DomainUser>>
        get() = _propertiesF

    private var _filteredPropertiesA: MutableLiveData<List<DomainUser>> = copyLiveData()
    val filteredProperties: LiveData<List<DomainUser>>
        get() = _filteredPropertiesA

    private lateinit var fList: List<DomainUser>
    //lateinit var fList: List<DomainUser>

    private fun initDataRefresh() {
        if (!initRefresh.refreshCheck()) {
            refreshDataFromNetwork()
        }
    }

    private fun refreshDataFromNetwork() {

        viewModelScope.launch {
            _status.value = UserApiStatus.LOADING
            try {
                projectListUseCases.refreshEntities()
                _status.value = UserApiStatus.DONE

                initRefresh.refreshOk()
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = UserApiStatus.ERROR
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        //viewModelJob.cancel()
    }

    fun displayUserDetails(user: DomainUser) {
        _navigateToSelectedUser.value = user
    }

    fun displayUserDetailsComplete() {
        _navigateToSelectedUser.value = null
    }

    fun setFilteredLiveData() {
        _filteredPropertiesA.value = fList
    }

    private fun copyLiveData(): MutableLiveData<List<DomainUser>> {
        return Transformations.map(properties) {
            it.deepCopy()
        } as MutableLiveData<List<DomainUser>>
    }

    fun resetLiveData() {
        _filteredPropertiesA.value = null
        _filteredPropertiesA = copyLiveData()
    }

    private fun returnProperties(): List<DomainUser> {
        return properties.value!!
    }

    fun propertiesFiltering(keyWord: String) {

        val newList: List<DomainUser> = returnProperties().deepCopy()

        fList = newList.filter {
            it.firstName.startsWith(keyWord, true) || it.lastName.startsWith(keyWord, true)
        }
        //Log.i("test", "propertiesFiltering тред ${Thread.currentThread().name}")
    }

}


class HomeViewModelFactory @Inject constructor(
    private val myViewModelProvider: Provider<HomeViewModel>,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return myViewModelProvider.get() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}