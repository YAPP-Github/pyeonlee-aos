package com.peonlee.model.type

enum class EventType(
    val eventName: String,
    val eventDataName: String
) {
    ALL("행사 전체", ""),
    ONE_PLUS_ONE("1 + 1", "ONE_PLUS_ONE"),
    TWO_PLUS_ONE("2 + 1", "TWO_PLUS_ONE")
}
