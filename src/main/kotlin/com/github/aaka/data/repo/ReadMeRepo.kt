package com.github.aaka.data.repo

import java.io.File
import javax.inject.Inject

class ReadMeRepo @Inject constructor() {
    fun getReadMeModel(): String {
        return File("README.model.md").readText()
    }

    fun saveReadMe(readMe: String) {
        File("README.md").writeText(readMe)
    }
}