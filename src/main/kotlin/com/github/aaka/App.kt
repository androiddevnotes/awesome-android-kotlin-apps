package com.github.aaka

import com.github.aaka.data.GitHubRepo
import com.github.aaka.data.repo.InputDataRepo
import com.github.aaka.di.DaggerAppComponent
import com.github.aaka.data.local.InputProjectCategory
import com.github.aaka.data.local.Project
import com.github.aaka.data.repo.ReadMeRepo
import com.github.aaka.tasks.convert.input
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.io.File
import java.lang.StringBuilder
import javax.inject.Inject

fun main(args: Array<String>) = runBlocking {
    App().run()
}

class App {

    @Inject
    lateinit var githubRepo: GitHubRepo

    @Inject
    lateinit var inputDataRepo: InputDataRepo

    @Inject
    lateinit var readMeRepo: ReadMeRepo

    suspend fun run() {

        // Init DI first
        DaggerAppComponent
            .builder()
            .build()
            .inject(this)


        val inputProjectCategories = inputDataRepo.getInputProjectCategories()
        val totalInputProjectCount = inputProjectCategories.sumBy { it.inputProjects.size }
        val projectMap = getProjectsMap(inputProjectCategories)

        // This is to make sure that all project details are collected
        require(projectMap.size == totalInputProjectCount) {
            """
                Expected $totalInputProjectCount but found only ${projectMap.size}.
                Failed to get some project details.
            """.trimIndent()
        }

        // Now let's go build the README.md
        val updatedReadMe = generateReadMe(inputProjectCategories, projectMap)
        File("README.md").writeText(updatedReadMe)
        println("Done!")

    }

    private fun generateReadMe(
        inputProjectCategories: List<InputProjectCategory>,
        projectMap: Map<String, Project>
    ): String {

        var readMeModel = readMeRepo.getReadMeModel()

        for (category in inputProjectCategories) {
            val tableBuilder = StringBuilder(
                """
                | Name                                             | Author ✍️                                        | Description 🗒️                                                                                                                                                  | Reputation 💪                |
                |--------------------------------------------------|-------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|
            """.trimIndent()
            )
            for (inputProject in category.inputProjects) {

                val project = projectMap[inputProject.githubUrl]!!

                tableBuilder.append(
                    """
                    | [${project.repo}](${project.repoUrl}) | [${project.owner}](${project.ownerUrl}) | ${project.description} \n ${project.stack} | 🌟 ${project.reputation.stars} </br> 🍴 ${project.reputation.fork} </br> 👁️ ${project.reputation.watchers}  |
                """.trimIndent()
                )
            }

            readMeModel = readMeModel.replace(category.key, tableBuilder.toString())
        }

        return readMeModel
    }

    /**
     * To convert all projects into one single map
     */
    private suspend fun getProjectsMap(inputProjectCategories: List<InputProjectCategory>): Map<String, Project> {
        val projectsMap = mutableMapOf<String, Project>()

        for (projectCategory in inputProjectCategories) {
            for (inputProject in projectCategory.inputProjects) {

                val url = inputProject.githubUrl
                val s1 = url.split("/")

                val username = s1[3]
                val repoName = s1[4]

                try {
                    val githubRepo = githubRepo.getRepo(username, repoName)
                    val project = Project(
                        githubRepo.name!!,
                        githubRepo.htmlUrl!!,
                        githubRepo.description,
                        githubRepo.owner!!.login!!,
                        githubRepo.owner.htmlUrl!!,
                        Project.Reputation(
                            githubRepo.forks!!,
                            githubRepo.stargazersCount!!,
                            githubRepo.subscribersCount!!
                        ),
                        inputProject.stack
                    )
                    projectsMap[inputProject.githubUrl] = project
                    println("Finished : ${inputProject.githubUrl} -> $project")
                } catch (e: HttpException) {
                    println("Failed: ${inputProject.githubUrl}")
                    throw e
                }
            }
        }

        return projectsMap
    }
}