/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.data.database.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Support(
        @Json(name = "url")
        val urlA: String,
        val text: String,
)