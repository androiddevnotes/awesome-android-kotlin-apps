package com.github.aaka.data.repo

import com.github.aaka.data.local.InputProjectCategory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File
import javax.inject.Inject

class InputDataRepo @Inject constructor(
    val moshi: Moshi
) {

    /**
     * Get input projects
     */
    fun getInputProjectCategories(): List<InputProjectCategory> {
        // Parsing data
        val dataJson = File("input_data.json").readText()
        val dataItemListType = Types.newParameterizedType(List::class.java, InputProjectCategory::class.java)
        val inputDataAdapter = moshi.adapter<List<InputProjectCategory>>(dataItemListType)
        return inputDataAdapter.fromJson(dataJson)!!
    }
}