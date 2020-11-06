package com.github.aaka.data.local

import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class InputProjectCategory(
    @Json(name = "key")
    val key: String, // $OTHER_REPOS
    @Json(name = "pattern")
    val pattern: String, // Other
    @Json(name = "projects")
    val inputProjects: List<InputProject>
) {
    @JsonClass(generateAdapter = true)
    data class InputProject(
        @Json(name = "github_url")
        val githubUrl: String, // https://github.com/shadowsocks/shadowsocks-android
        @Json(name = "stack")
        val stack: String? // Testing, Room,  Firebase Ads, WorkManager
    )
}