package com.example.data.repository

import com.example.data.datasource.ReservationDataSource
import com.example.domain.model.AvailableReservationTime
import com.example.domain.repository.ReservationRepository
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationDataSource: ReservationDataSource
) : ReservationRepository {
    override suspend fun getReservationTime(studioId: Int, date: String): AvailableReservationTime {
        return reservationDataSource.getReservationTime(studioId, date).toDomainModel()
    }
}