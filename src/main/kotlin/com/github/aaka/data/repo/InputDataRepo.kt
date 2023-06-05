package com.github.aaka.data.repo

import com.github.aaka.data.local.InputProjectCategory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File
import javax.inject.Inject

class InputDataRepo @Inject constructor(
    val moshi: Moshi
) {

    private val inputDataFile = File("input_data.json")
    private val inputDataAdapter = moshi.adapter<List<InputProjectCategory>>(
        Types.newParameterizedType(List::class.java, InputProjectCategory::class.java)
    ).indent("  ")

    /**
     * Get input projects
     */
    fun getInputProjectCategories(): List<InputProjectCategory> {
        // Parsing data

        val dataJson = inputDataFile.readText()
        return inputDataAdapter.fromJson(dataJson)!!
            /*.map {
                it.copy(
                    inputProjects = if (it.inputProjects.size > 5) {
                        it.inputProjects.subList(0, 2)
                    } else {
                        it.inputProjects.subList(0, 1)
                    }
                )
            }*/
    }

    fun updateInputProjectCategories(inputData : List<InputProjectCategory>){
        val dataJson = inputDataAdapter.toJson(inputData)
        inputDataFile.writeText(dataJson)
    }
}