/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common.di.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.savedstate.SavedStateRegistryOwner
import com.palushkin.domain.repository.ProjectRepository
import com.palushkin.domain.service.ProjectListInteractor
import com.palushkin.domain.service.ProjectListUseCases
import com.palushkin.kotlintestapp.common.KeyboardInterface
import com.palushkin.kotlintestapp.common.KeyboardManager
import com.palushkin.kotlintestapp.ui.home.RxListFilter
import com.palushkin.kotlintestapp.ui.home.RxListFilterInterface
import com.palushkin.kotlintestapp.ui.notifications.MessageProvider
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val savedStateRegistryOwner: SavedStateRegistryOwner) {

    @Provides
    @PresentationScope
    fun providesProjectListUseCases(projectRepository: ProjectRepository): ProjectListUseCases = ProjectListInteractor(projectRepository)

    @Provides
    @PresentationScope
    //fun messageProvider(activity: AppCompatActivity) = MessageProvider(activity)
    fun messageProvider(activity: AppCompatActivity) = MessageProvider(activity as Context)

    @Provides
    fun savedStateRegistryOwner() = savedStateRegistryOwner

    /*@Provides
    @PresentationScope
    fun keyboardManagerProvider(activity: AppCompatActivity) = KeyboardManager(activity as Context)*/
    @Provides
    @PresentationScope
    fun keyboardManagerProvider(activity: AppCompatActivity): KeyboardInterface {
        return KeyboardManager(activity as Context)
    }

    /*@Provides
    @PresentationScope
    fun rxListFilterProvider() = RxListFilter()*/
    @Provides
    @PresentationScope
    fun rxListFilterProvider() : RxListFilterInterface {
        return RxListFilter()
    }

}
