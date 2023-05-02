package com.example.applauncher.data.model

import com.squareup.moshi.Json

/**
 * Apis Response which will contain the record
 */
data class BlockedApiResponse(
    @Json(name = "record")
    val record: Record
)

/**
 * Data class which contains the list of denied apps
 */
data class Record(
    @Json(name = "denylist")
    val denyList: List<String>
)