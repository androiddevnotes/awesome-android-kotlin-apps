package com.github.aaka.tasks.generate

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import java.io.File

/**
 * To update README.md with latest data
 */
fun main(args: Array<String>) {
    generateServerData()
}

fun generateServerData() {

    val moshi = Moshi.Builder()
            .build()

    // Parsing data
    val dataJson = File("input_data.json").readText()
    val type = Types.newParameterizedType(List::class.java, InputDataItem::class.java)
    val dataAdapter = moshi.adapter<List<InputDataItem>>(type)
    val data = dataAdapter.fromJson(dataJson)!!

    val totalInputProjectCount = data.sumBy { it.inputProjects.size }

    // Preparing GitHub API
    val githubToken = System.getenv("MOVIE_MONK_GITHUB_ACCESS_TOKEN")

    val okHttpClient = OkHttpClient.Builder()
            .build()

    val projectMap = mutableMapOf<String, Project>()

    mainLoop@ for (category in data) {

        for (inputProject in category.inputProjects) {

            val url = inputProject.githubUrl
            val s1 = url.split("/")

            val username = s1[3]
            val repoName = s1[4]

            val targetUrl = "https://api.github.com/repos/$username/$repoName"
            val request = Request.Builder()
                    .url(targetUrl)
                    .addHeader("Accept", "application/vnd.github.v3+json")
                    .addHeader("Authorization", "token $githubToken")
                    .build()

            val resp = okHttpClient.newCall(request).execute()
            if (resp.isSuccessful) {
                val body = resp.body?.string()
                if (body != null) {
                    val githubRepoResponseAdapter = GithubRepoResponseJsonAdapter(moshi)
                    val githubRepo = githubRepoResponseAdapter.fromJson(body)!!
                    val project = Project(
                            githubRepo.name!!,
                            githubRepo.htmlUrl!!,
                            githubRepo.description ?: "",
                            githubRepo.owner!!.login!!,
                            githubRepo.owner.htmlUrl!!,
                            Project.Reputation(
                                    githubRepo.forks!!,
                                    githubRepo.stargazersCount!!,
                                    githubRepo.watchersCount!!
                            ),
                            inputProject.stack
                    )
                    projectMap[inputProject.githubUrl] = project
                    println("Finished : ${inputProject.githubUrl} -> $project")
                } else {
                    println("Failed to get body : $targetUrl")
                    break@mainLoop
                }
            } else {
                println("Failed to get data from $targetUrl")
                break@mainLoop
            }
        }
    }


    require(projectMap.size == totalInputProjectCount) { "Failed to get some projects" }

    val mapAdapter = Types.newParameterizedType(Map::class.java, String::class.java, Project::class.java)
    val remoteJson = moshi.adapter<Map<String, Project>>(mapAdapter).toJson(projectMap)
    File("server_data.json").writeText(remoteJson)

    println("Done!")
}
