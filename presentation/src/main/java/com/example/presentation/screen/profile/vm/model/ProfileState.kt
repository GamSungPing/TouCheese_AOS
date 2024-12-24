package com.example.presentation.screen.profile.vm.model

data class ProfileState(
    val loggedIn: Boolean,
    val memberId: Long,
    val memberName: String,
    val selectedOption : Options? = null
){
    companion object{
        fun create(): ProfileState{
            return ProfileState(
                loggedIn = false,
                memberId = 0L,
                memberName = ""
            )
        }
    }
}