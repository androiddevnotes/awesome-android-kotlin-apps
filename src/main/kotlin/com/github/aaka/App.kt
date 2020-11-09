package com.github.aaka

import com.github.aaka.core.ReadMeGenerator
import com.github.aaka.data.repo.GitHubRepo
import com.github.aaka.data.repo.InputDataRepo
import com.github.aaka.di.DaggerAppComponent
import com.github.aaka.data.local.InputProjectCategory
import com.github.aaka.data.local.Project
import com.github.aaka.data.repo.ReadMeRepo
import com.github.aaka.utils.DateTimeUtils
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.system.exitProcess

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
        val readMeModel = readMeRepo.getReadMeModel()
        val updatedReadMe = ReadMeGenerator.generateReadMe(readMeModel, inputProjectCategories, projectMap)
        readMeRepo.saveReadMe(updatedReadMe)
        println("Done!")
        exitProcess(0)
    }


    /**
     * To convert all projects into one single map with all details collected from GitHub API
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
                        DateTimeUtils.fromUtcToUtcMillis(githubRepo.pushedAt),
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