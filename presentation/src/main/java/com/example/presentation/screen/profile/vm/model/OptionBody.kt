package com.example.presentation.screen.profile.vm.model

enum class OptionBody(
    override val title: String,
    val content: String? = null
): Options {
    TermsAndPolicy("약관 및 정책"),
    OpenSourceLicense("오픈소스 라이선스"),
//    ClearCache("캐시 데이터 삭제하기"),
//    AppVersion("앱 버전"),
    ContactEmail("문의 메일")
}
