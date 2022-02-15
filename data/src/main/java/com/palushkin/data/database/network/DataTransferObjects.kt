/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.data.database.network

import android.os.Parcelable
import com.palushkin.data.database.DatabaseEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class UserList(

        @Json(name = "data")
        val dataU: List<User>
)

@Parcelize
@JsonClass(generateAdapter = true)
data class User(

        val id: String,
        val email: String,
        @Json(name = "first_name")
        val firstName: String,
        @Json(name = "last_name")
        val lastName: String,
        @Json(name = "avatar")
        val imgSrcUrl: String

) : Parcelable

fun UserList.asDatabaseModel(): Array<DatabaseEntity> {
    return dataU.map {
        DatabaseEntity(
                id = it.id,
                email = it.email,
                firstName = it.firstName,
                lastName = it.lastName,
                imgSrcUrl = it.imgSrcUrl
        )
    }.toTypedArray()
}