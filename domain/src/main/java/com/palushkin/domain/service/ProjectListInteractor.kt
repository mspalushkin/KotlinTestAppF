/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.domain.service

import com.palushkin.domain.repository.ProjectRepository

class ProjectListInteractor(
    private val projectRepository: ProjectRepository
) : ProjectListUseCases {

    override suspend fun refreshEntities() {
        projectRepository.refreshEntities()
    }
}