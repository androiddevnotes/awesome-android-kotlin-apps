package com.github.aaka.data.local

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Project(
    @Json(name = "repo")
    val repo: String, // NotyKT
    @Json(name = "repo_url")
    val repoUrl: String, // https://github.com/PatilShreyas/NotyKT
    @Json(name = "description")
    val description: String?, // NotyKT is a complete Kotlin-stack (Backend + Android) application built to demonstrate the use of Modern development tools with best practices implementation.
    @Json(name = "owner")
    val owner: String, // PatilShreyas
    @Json(name = "owner_url")
    val ownerUrl: String, // https://github.com/PatilShreyas
    @Json(name = "reputation")
    val reputation: Reputation,
    @Json(name = "stack")
    val stack: String? // This is stack information
) {
    @JsonClass(generateAdapter = true)
    data class Reputation(
        @Json(name = "fork")
        val fork: Int, // 2
        @Json(name = "stars")
        val stars: Int, // 12
        @Json(name = "watchers")
        val watchers: Int // 2
    )
}