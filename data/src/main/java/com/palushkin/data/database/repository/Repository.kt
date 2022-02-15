/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.data.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.palushkin.domain.repository.ProjectRepository
import com.palushkin.data.database.EntityDatabase
import com.palushkin.data.database.asDomainModel
import com.palushkin.domain.domain.DomainUserClean
import com.palushkin.data.database.network.UserApiService
import com.palushkin.data.database.network.UserList
import com.palushkin.data.database.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class Repository(
    private val database: EntityDatabase,
    private val userApiService: UserApiService,
) : ProjectRepository {

    val domainUsers: LiveData<List<DomainUserClean>> =
        Transformations.map(database.userDao.getUsers()) {
            it.asDomainModel()
        }

    val domainUsersFlow: Flow<List<DomainUserClean>>
        get() = database.userDao.getUsersFlow().map { value -> value.asDomainModel() }

    override suspend fun refreshEntities() {
        withContext(Dispatchers.IO) {
            val userList: UserList = userApiService.getUserList()
            database.userDao.insertAll(*userList.asDatabaseModel())
        }
    }
}