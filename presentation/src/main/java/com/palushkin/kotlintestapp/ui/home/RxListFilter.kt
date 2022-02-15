/*
 * 04.08.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.lang.Thread.currentThread
import java.util.*
import java.util.concurrent.TimeUnit

class RxListFilter : RxListFilterInterface {

    private fun fromView(searchView: SearchView): Observable<String> {

        val subject: PublishSubject<String> = PublishSubject.create()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                subject.onNext(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                subject.onNext(newText!!)
                return true
            }

        })

        return subject
    }

    private fun fromViewFlow(searchView: SearchView): StateFlow<String> {

        val queryText = MutableStateFlow("")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    queryText.value = query
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    queryText.value = newText
                }
                return true
            }
        })

        return queryText
    }

    @SuppressLint("CheckResult", "DefaultLocale")
    override fun setUpSearchObservable(viewModel: HomeViewModel, searchView: SearchView) {

        //RxSearchObservable.fromView(binding.searchView)
        fromView(searchView)
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { text -> text.toLowerCase().trim() }
            //.filter { text -> text.isNotBlank() }
            .distinctUntilChanged()
            .doOnNext { text ->

                when (text.isNotEmpty()) {
                    true -> {
                        //Log.i("test", "doOnNext")
                        //Log.i("test", "тред ${currentThread().name}")
                        viewModel.propertiesFiltering(text)
                    }
                    else -> {
                        //viewModel.
                    }
                }

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { text ->

                    when (text.isNotEmpty()) {
                        true -> {
                            viewModel.setFilteredLiveData()

                            //Log.i("test", "subscriber: $text")
                            //Log.i("test", "тред ${currentThread().name}")
                        }
                        else -> {
                            if (viewModel.filteredProperties.value!!.size != viewModel.properties.value!!.size) {
                                viewModel.resetLiveData()
                                //Log.i("test", "пусто")
                            }
                        }
                    }

                    //Log.i("test", "subscriber: ${viewModel.properties.value!![0].firstName}")
                },
                { _ ->
                    //Log.e("test", "ошибка")
                },
                {
                    //Log.i("test", "onComplete")
                }
            )

    }

    override suspend fun setUpSearchFlowable(viewModel: HomeViewModel, searchView: SearchView) {

        fromViewFlow(searchView)
            .debounce(300)
            .map { text -> text.toLowerCase(Locale.ROOT).trim() }
            .distinctUntilChanged()
            .onEach { text ->
                when (text.isNotEmpty()) {
                    true -> {
                        //Log.i("test", "onEach тред ${currentThread().name}")
                        viewModel.propertiesFiltering(text)
                    }
                    else -> {
                        //viewModel.
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            //.catch {  }
            .collect { text ->
                when (text.isNotEmpty()) {
                    true -> {
                        //Log.i("test", "collect ${viewModel.fList.first().firstName}")
                        //Log.i("test", "collect тред ${currentThread().name}")
                        viewModel.setFilteredLiveData()

                        //Log.i("test", "subscriber: $text")
                        //Log.i("test", "тред ${currentThread().name}")
                    }
                    else -> {
                        if (viewModel.filteredProperties.value!!.size != viewModel.properties.value!!.size) {
                            viewModel.resetLiveData()
                            //Log.i("test", "пусто")
                        }
                    }
                }
            }

    }

}

interface RxListFilterInterface {
    fun setUpSearchObservable(viewModel: HomeViewModel, searchView: SearchView)
    suspend fun setUpSearchFlowable(viewModel: HomeViewModel, searchView: SearchView)
}