/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.data.database.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import retrofit2.http.GET

const val BASE_URL = "https://reqres.in/api/"

class UserAdapter {

    @WrappedRepoList
    @FromJson
    fun fromJson(json: UserList): List<User> {
        return json.dataU
    }

    @ToJson
    fun toJson(@WrappedRepoList value: List<User>): UserList {
        throw UnsupportedOperationException()
    }
}

interface UserApiService {

    @GET("users")
    @WrappedRepoList
    suspend fun getUsers(): List<User>

    @GET("users")
    suspend fun getUserList(): UserList
}