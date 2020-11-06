package com.github.aaka.core

import com.github.aaka.data.local.InputProjectCategory
import com.github.aaka.data.local.Project
import com.github.aaka.data.repo.ReadMeRepo
import java.lang.StringBuilder

object ReadMeGenerator {
    fun generateReadMe(
        _readMeModel : String,
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

                tableBuilder.append(
                    """
                    
                    | [${project.repo}](${project.repoUrl}) | [${project.owner}](${project.ownerUrl}) | $description | 🌟 ${project.reputation.stars} </br> 🍴 ${project.reputation.fork} </br> 👁️ ${project.reputation.watchers}  |
                """.trimIndent()
                )
            }

            readMeModel = readMeModel.replace(category.key, tableBuilder.toString())
        }

        return readMeModel
    }
}