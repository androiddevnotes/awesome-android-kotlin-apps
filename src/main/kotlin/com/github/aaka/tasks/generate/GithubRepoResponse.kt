package com.github.aaka.tasks.generate
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class GithubRepoResponse(
    @Json(name = "archive_url")
    val archiveUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/{archive_format}{/ref}
    @Json(name = "archived")
    val archived: Boolean?, // false
    @Json(name = "assignees_url")
    val assigneesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/assignees{/user}
    @Json(name = "blobs_url")
    val blobsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/git/blobs{/sha}
    @Json(name = "branches_url")
    val branchesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/branches{/branch}
    @Json(name = "clone_url")
    val cloneUrl: String?, // https://github.com/androiddevnotes/awesome-jetpack-compose-android-apps.git
    @Json(name = "collaborators_url")
    val collaboratorsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/collaborators{/collaborator}
    @Json(name = "comments_url")
    val commentsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/comments{/number}
    @Json(name = "commits_url")
    val commitsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/commits{/sha}
    @Json(name = "compare_url")
    val compareUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/compare/{base}...{head}
    @Json(name = "contents_url")
    val contentsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/contents/{+path}
    @Json(name = "contributors_url")
    val contributorsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/contributors
    @Json(name = "created_at")
    val createdAt: String?, // 2020-08-28T19:50:05Z
    @Json(name = "default_branch")
    val defaultBranch: String?, // master
    @Json(name = "deployments_url")
    val deploymentsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/deployments
    @Json(name = "description")
    val description: String?, // [Hacktoberfest] 👓 A curated list of awesome Jetpack Compose android apps by open-source contributors.
    @Json(name = "disabled")
    val disabled: Boolean?, // false
    @Json(name = "downloads_url")
    val downloadsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/downloads
    @Json(name = "events_url")
    val eventsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/events
    @Json(name = "fork")
    val fork: Boolean?, // false
    @Json(name = "forks")
    val forks: Int?, // 9
    @Json(name = "forks_count")
    val forksCount: Int?, // 9
    @Json(name = "forks_url")
    val forksUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/forks
    @Json(name = "full_name")
    val fullName: String?, // androiddevnotes/awesome-jetpack-compose-android-apps
    @Json(name = "git_commits_url")
    val gitCommitsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/git/commits{/sha}
    @Json(name = "git_refs_url")
    val gitRefsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/git/refs{/sha}
    @Json(name = "git_tags_url")
    val gitTagsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/git/tags{/sha}
    @Json(name = "git_url")
    val gitUrl: String?, // git://github.com/androiddevnotes/awesome-jetpack-compose-android-apps.git
    @Json(name = "has_downloads")
    val hasDownloads: Boolean?, // true
    @Json(name = "has_issues")
    val hasIssues: Boolean?, // true
    @Json(name = "has_pages")
    val hasPages: Boolean?, // false
    @Json(name = "has_projects")
    val hasProjects: Boolean?, // true
    @Json(name = "has_wiki")
    val hasWiki: Boolean?, // true
    @Json(name = "homepage")
    val homepage: String?,
    @Json(name = "hooks_url")
    val hooksUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/hooks
    @Json(name = "html_url")
    val htmlUrl: String?, // https://github.com/androiddevnotes/awesome-jetpack-compose-android-apps
    @Json(name = "id")
    val id: Int?, // 291133502
    @Json(name = "issue_comment_url")
    val issueCommentUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/issues/comments{/number}
    @Json(name = "issue_events_url")
    val issueEventsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/issues/events{/number}
    @Json(name = "issues_url")
    val issuesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/issues{/number}
    @Json(name = "keys_url")
    val keysUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/keys{/key_id}
    @Json(name = "labels_url")
    val labelsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/labels{/name}
    @Json(name = "language")
    val language: String?, // Kotlin
    @Json(name = "languages_url")
    val languagesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/languages
    @Json(name = "license")
    val license: Any?, // null
    @Json(name = "merges_url")
    val mergesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/merges
    @Json(name = "milestones_url")
    val milestonesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/milestones{/number}
    @Json(name = "mirror_url")
    val mirrorUrl: Any?, // null
    @Json(name = "name")
    val name: String?, // awesome-jetpack-compose-android-apps
    @Json(name = "network_count")
    val networkCount: Int?, // 9
    @Json(name = "node_id")
    val nodeId: String?, // MDEwOlJlcG9zaXRvcnkyOTExMzM1MDI=
    @Json(name = "notifications_url")
    val notificationsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/notifications{?since,all,participating}
    @Json(name = "open_issues")
    val openIssues: Int?, // 1
    @Json(name = "open_issues_count")
    val openIssuesCount: Int?, // 1
    @Json(name = "owner")
    val owner: Owner?,
    @Json(name = "private")
    val `private`: Boolean?, // false
    @Json(name = "pulls_url")
    val pullsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/pulls{/number}
    @Json(name = "pushed_at")
    val pushedAt: String?, // 2020-10-16T19:33:59Z
    @Json(name = "releases_url")
    val releasesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/releases{/id}
    @Json(name = "size")
    val size: Int?, // 408
    @Json(name = "ssh_url")
    val sshUrl: String?, // git@github.com:androiddevnotes/awesome-jetpack-compose-android-apps.git
    @Json(name = "stargazers_count")
    val stargazersCount: Int?, // 83
    @Json(name = "stargazers_url")
    val stargazersUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/stargazers
    @Json(name = "statuses_url")
    val statusesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/statuses/{sha}
    @Json(name = "subscribers_count")
    val subscribersCount: Int?, // 1
    @Json(name = "subscribers_url")
    val subscribersUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/subscribers
    @Json(name = "subscription_url")
    val subscriptionUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/subscription
    @Json(name = "svn_url")
    val svnUrl: String?, // https://github.com/androiddevnotes/awesome-jetpack-compose-android-apps
    @Json(name = "tags_url")
    val tagsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/tags
    @Json(name = "teams_url")
    val teamsUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/teams
    @Json(name = "temp_clone_token")
    val tempCloneToken: Any?, // null
    @Json(name = "trees_url")
    val treesUrl: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps/git/trees{/sha}
    @Json(name = "updated_at")
    val updatedAt: String?, // 2020-11-06T09:30:05Z
    @Json(name = "url")
    val url: String?, // https://api.github.com/repos/androiddevnotes/awesome-jetpack-compose-android-apps
    @Json(name = "watchers")
    val watchers: Int?, // 83
    @Json(name = "watchers_count")
    val watchersCount: Int? // 83
) {
    @JsonClass(generateAdapter = true)
    data class Owner(
        @Json(name = "avatar_url")
        val avatarUrl: String?, // https://avatars2.githubusercontent.com/u/66256957?v=4
        @Json(name = "events_url")
        val eventsUrl: String?, // https://api.github.com/users/androiddevnotes/events{/privacy}
        @Json(name = "followers_url")
        val followersUrl: String?, // https://api.github.com/users/androiddevnotes/followers
        @Json(name = "following_url")
        val followingUrl: String?, // https://api.github.com/users/androiddevnotes/following{/other_user}
        @Json(name = "gists_url")
        val gistsUrl: String?, // https://api.github.com/users/androiddevnotes/gists{/gist_id}
        @Json(name = "gravatar_id")
        val gravatarId: String?,
        @Json(name = "html_url")
        val htmlUrl: String?, // https://github.com/androiddevnotes
        @Json(name = "id")
        val id: Int?, // 66256957
        @Json(name = "login")
        val login: String?, // androiddevnotes
        @Json(name = "node_id")
        val nodeId: String?, // MDQ6VXNlcjY2MjU2OTU3
        @Json(name = "organizations_url")
        val organizationsUrl: String?, // https://api.github.com/users/androiddevnotes/orgs
        @Json(name = "received_events_url")
        val receivedEventsUrl: String?, // https://api.github.com/users/androiddevnotes/received_events
        @Json(name = "repos_url")
        val reposUrl: String?, // https://api.github.com/users/androiddevnotes/repos
        @Json(name = "site_admin")
        val siteAdmin: Boolean?, // false
        @Json(name = "starred_url")
        val starredUrl: String?, // https://api.github.com/users/androiddevnotes/starred{/owner}{/repo}
        @Json(name = "subscriptions_url")
        val subscriptionsUrl: String?, // https://api.github.com/users/androiddevnotes/subscriptions
        @Json(name = "type")
        val type: String?, // User
        @Json(name = "url")
        val url: String? // https://api.github.com/users/androiddevnotes
    )
}