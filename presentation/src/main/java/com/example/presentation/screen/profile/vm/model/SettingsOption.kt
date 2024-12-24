package com.example.presentation.screen.profile.vm.model

enum class SettingsOption(
    override val title: String,
): Options {
    TermsAndPolicy("약관 및 정책"),
    OpenSourceLicense("오픈소스 라이선스"),
    ClearCache("캐시 데이터 삭제하기"),
    AppVersion("앱 버전"),
    ContactEmail("문의 메일"),
    Logout("로그아웃"),
    WithdrawMembership("회원 탈퇴")
}
