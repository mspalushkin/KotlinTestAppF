/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.domain.service

interface ProjectListUseCases {

    //val domainUsers: Single<List<DomainUserClean>>
    suspend fun refreshEntities() : Unit
}