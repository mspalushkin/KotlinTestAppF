/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.palushkin.domain.domain.DomainUserClean

@Entity
data class DatabaseEntity constructor(

        @PrimaryKey
        val id: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val imgSrcUrl: String
)

fun List<DatabaseEntity>.asDomainModel(): List<DomainUserClean> {
    return map {
        DomainUserClean(
                id = it.id,
                email = it.email,
                firstName = it.firstName,
                lastName = it.lastName,
                imgSrcUrl = it.imgSrcUrl
        )
    }
}