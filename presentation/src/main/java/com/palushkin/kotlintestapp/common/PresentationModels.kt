/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.common

import android.os.Parcelable
import com.palushkin.domain.domain.DomainUserClean
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainUser constructor(

    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val imgSrcUrl: String

) : Parcelable

fun List<DomainUserClean>.asMVModel(): List<DomainUser> {

    return map {
        DomainUser(
            id = it.id,
            email = it.email,
            firstName = it.firstName,
            lastName = it.lastName,
            imgSrcUrl = it.imgSrcUrl
        )
    }
}

/*
fun List<DomainUser>.deepCopy(): List<DomainUser>{
    return map {
        DomainUser(
            id = it.id,
            email = it.email,
            firstName = it.firstName,
            lastName = it.lastName,
            imgSrcUrl = it.imgSrcUrl
        )
    }
}*/
fun List<DomainUser>.deepCopy(): List<DomainUser>{
    return map {
        it.copy()
    }
}
