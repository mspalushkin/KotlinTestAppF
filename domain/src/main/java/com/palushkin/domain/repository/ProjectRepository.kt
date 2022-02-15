/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.domain.repository

interface ProjectRepository {

    suspend fun refreshEntities() : Unit
}