package com.github.aaka.core

import com.github.aaka.data.local.InputProjectCategory
import com.github.aaka.data.local.Project
import com.github.aaka.data.repo.ReadMeRepo
import com.github.aaka.utils.DateTimeUtils
import java.lang.StringBuilder
import java.util.*

object ReadMeGenerator {

    private const val KEY_LAST_UPDATED = "\$LAST_UPDATED"
    private const val KEY_APPS_COUNT = "\$APPS_COUNT"

    fun generateReadMe(
        _readMeModel: String,
        inputProjectCategories: List<InputProjectCategory>,
        projectMap: Map<String, Project>
    ): String {

        var readMeModel = _readMeModel


        for (category in inputProjectCategories) {
            val tableBuilder = StringBuilder(
                """
                | Name                                             | Author ✍️                                        | Description 🗒️                                                                                                                                                  | Reputation 💪                |
                |--------------------------------------------------|-------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|
            """.trimIndent()
            )

            // Sorting project by last commit
            category.inputProjects =
                category.inputProjects.sortedByDescending { projectMap[it.githubUrl]?.lastCommitInUtcMillis }

            for (inputProject in category.inputProjects) {

                val project =
                    projectMap[inputProject.githubUrl] ?: error("Couldn't find ${inputProject.githubUrl} in projectMap")

                var description = ""
                if (project.description != null) {
                    description = project.description
                }

                if (project.stack != null) {
                    description += "</br></br> <b>Tech Stack</b> : ${project.stack} "
                }

                description += "</br></br> <i> Last commit: ${DateTimeUtils.getRelativeTimeSpan(project.lastCommitInUtcMillis)}</i>"

                tableBuilder.append(
                    """
                    
                    | [${
                        project.repo.replace(
                            "_",
                            "-"
                        )
                    }](${project.repoUrl}) | [${project.owner}](${project.ownerUrl}) | $description | 🌟 ${project.reputation.stars} </br> 🍴 ${project.reputation.fork} </br> 👁️ ${project.reputation.watchers}  |
                """.trimIndent()
                )
            }

            readMeModel = readMeModel.replace(category.key, tableBuilder.toString())
        }

        // Add last updated date
        readMeModel = readMeModel.replace(KEY_LAST_UPDATED, Date().toString())

        // Update total apps count
        readMeModel = readMeModel.replace(KEY_APPS_COUNT, projectMap.size.toString())

        return readMeModel
    }
}