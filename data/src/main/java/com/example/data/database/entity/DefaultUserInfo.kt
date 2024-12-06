package com.example.data.database.entity

import com.example.domain.model.DefaultReserveInfo
import kotlinx.serialization.Serializable

@Serializable
internal data class DefaultUserInfo (
    val phone: String = "",
    val email: String = "",
){
    fun toDomainModel(): DefaultReserveInfo{
        return DefaultReserveInfo(
            phone = phone,
            email = email
        )
    }
}