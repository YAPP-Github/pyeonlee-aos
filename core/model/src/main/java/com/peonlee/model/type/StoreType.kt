package com.peonlee.model.type

/**
 * 편의점 type
 */
enum class StoreType(
    val storeName: String,
    val storeDataName: String
) {
    CU("CU", "CU"),
    GS25("GS25", "GS"),
    SEVEN("세븐일레븐", "SEVEN_ELEVEN")
}
