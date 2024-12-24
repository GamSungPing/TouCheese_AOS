package com.example.domain.usecase

import com.example.domain.model.StudioInfoWithConcept
import com.example.domain.repository.like.LikeRepository
import com.example.domain.repository.studio.StudioRepository
import javax.inject.Inject

class LikeStudioUseCase @Inject constructor(
    private val studioRepository: StudioRepository,
    private val likeRepository: LikeRepository
) {

    suspend fun getCommonStudio(conceptId: Int, memberId: Int): List<StudioInfoWithConcept> {
        val studios = studioRepository.getStudioOnlyConcept(conceptId)
        val likedStudios = likeRepository.getLikes(memberId)

        return studios.map { studio ->
            if (likedStudios.any { likedStudio -> likedStudio.id == studio.id }) {
                studio.copy(isSelected = true)
            } else {
                studio.copy(isSelected = false)
            }
        }
    }
}