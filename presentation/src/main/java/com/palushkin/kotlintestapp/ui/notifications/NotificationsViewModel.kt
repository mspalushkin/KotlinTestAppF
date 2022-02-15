/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.notifications

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject
import javax.inject.Provider

class NotificationsViewModel @Inject constructor(
    private val messageProvider: MessageProvider,
    state: SavedStateHandle
    ) : ViewModel() {

    companion object {
        private const val TEXT_KEY = "text"
    }

    private val savedStateHandle = state



    private val _text = MutableLiveData<String>().apply {
        value = messageProvider.getMessage()
    }
    val text: LiveData<String> = _text


    init {
    }


    private fun saveCurrentState(text: String){

        savedStateHandle.set(TEXT_KEY, text)
    }

    private fun getCurrentState(): LiveData<String>? {

        return when (savedStateHandle.contains(TEXT_KEY)){
          true -> savedStateHandle.getLiveData(TEXT_KEY)
            else -> null
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

}


class NotificationsViewModelFactory @Inject constructor(

    //private val myViewModelProvider: Provider<NotificationsViewModel>,
    private val messageProvider: Provider<MessageProvider>,
    savedStateRegistryOwner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        @Suppress("unchecked_cast")
        return when(modelClass) {
            NotificationsViewModel::class.java -> NotificationsViewModel(
                messageProvider.get(),
                handle
            ) as T
            else -> throw RuntimeException("unsupported ViewModel type: $modelClass")
        }
    }

}