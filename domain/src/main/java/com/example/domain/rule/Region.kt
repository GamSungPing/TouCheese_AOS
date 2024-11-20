package com.example.domain.rule

sealed class Region(val id: Int, val name: String) {
    data object Gangnam : Region(1, "강남구")
    data object Seocho : Region(2, "서초구")
    data object Songpa : Region(3, "송파구")
    data object Gangseo : Region(4, "강서구")
    data object Mapo : Region(5, "마포구")
    data object Yeongdeungpo : Region(6, "영등포구")
    data object Gangbuk : Region(7, "강북구")
    data object Yongsan : Region(8, "용산구")
    data object Seongdong : Region(9, "성동구")
}
