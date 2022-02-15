/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common.di.app

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.palushkin.data.database.EntityDatabase
import com.palushkin.data.database.getDatabase
import com.palushkin.data.database.network.BASE_URL
import com.palushkin.data.database.network.UserAdapter
import com.palushkin.data.database.network.UserApiService
import com.palushkin.data.database.repository.Repository
import com.palushkin.domain.repository.ProjectRepository
import com.palushkin.kotlintestapp.ui.home.InitInterface
import com.palushkin.kotlintestapp.ui.home.InitRefresh
import com.palushkin.kotlintestapp.ui.home.getInitRefresh
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class AppModule(val application: Application) {

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun database(app: Application): EntityDatabase {
        return getDatabase(app)
    }

    @Provides
    @AppScope
    fun projectRepository(database: EntityDatabase, userApiService: UserApiService): ProjectRepository {
        return Repository(database, userApiService)
    }

    @Provides
    @AppScope
    fun repository(database: EntityDatabase, userApiService: UserApiService): Repository {
        return Repository(database, userApiService)
    }

    @Provides
    @AppScope
    fun moshi(): Moshi {
        return Moshi.Builder()
            .add(UserAdapter())
            .add(KotlinJsonAdapterFactory())
            //.addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @AppScope
    fun retrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            //.client(okHttpBuilder)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @AppScope
    fun apiService(retrofit: Retrofit): UserApiService = retrofit.create(UserApiService::class.java)

    @Provides
    @AppScope
    fun initRefresh(app: Application): InitInterface {
        return getInitRefresh(app)
    }

}