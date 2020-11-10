package com.github.aaka.tasks.convert

import java.lang.StringBuilder

val input = """

- https://github.com/shadowsocks/shadowsocks-android

    - A shadowsocks client for Android

    - Tech Stack = Testing, Room,  Firebase Ads, WorkManager


- https://github.com/BijoySingh/Scarlet-Notes

    - Simple yet powerful rich note taking android application, with a lot of flexibilty of usage


    - Tech Stack = Dagger, Coroutines, Room, Firebase Auth, Firebase Database, Paging, Navigation, Evernote android-job, Facebook Litho, Facebook SoLoader, Biometric
    
   
    
""".trimIndent()

private val urlRegEx = "(?<url>https:\\/\\/github\\.com\\/.+?\\/.+)".toRegex()
private val techStackRegEx = "Tech Stack = (?<stack>.+)".toRegex()

fun main(args: Array<String>) {

    // Parsing URL
    val urlResults = urlRegEx.findAll(input)
    val urls = mutableListOf<String>()
    for (url in urlResults) {
        urls.add(url.value)
    }

    // Parsing Stack
    val stackResults = techStackRegEx.findAll(input)
    val stacks = mutableListOf<String>()
    for (stack in stackResults) {
        stacks.add(stack.value)
    }

    // Checking data integrity
    require(stacks.size == urls.size) { "Failed" }

    // Creating JSON
    val sb = StringBuilder()
    for ((index, url) in urls.withIndex()) {
        val stack = stacks[index]
        val project = """
            {
                "github_url": "$url",
                "stack": "${stack.split("=")[1].trim()}"
            },
        """.trimIndent()
        sb.append(project)
    }

    // Printing JSON
    println(sb)

}