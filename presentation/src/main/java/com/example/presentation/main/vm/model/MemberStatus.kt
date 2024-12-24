package com.example.presentation.main.vm.model

sealed interface MemberStatus{
    data object Loading: MemberStatus
    data class Member(val memberId: Long) : MemberStatus
    data object NonMember : MemberStatus
}
