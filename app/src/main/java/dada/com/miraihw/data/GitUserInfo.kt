package dada.com.miraihw.data

import com.squareup.moshi.Json

data class GitUserInfo(
    @field:Json(name = "login")
     val login: String? = null,

    @field:Json(name = "id")
     val id: Int? = null,

    @field:Json(name = "node_id")
     val nodeId: String? = null,

    @field:Json(name = "avatar_url")
     val avatarUrl: String,

    @field:Json(name = "gravatar_id")
     val gravatarId: String? = null,

    @field:Json(name = "url")
     val url: String? = null,

    @field:Json(name = "html_url")
     val htmlUrl: String? = null,

    @field:Json(name = "followers_url")
     val followersUrl: String? = null,

    @field:Json(name = "following_url")
     val followingUrl: String? = null,

    @field:Json(name = "gists_url")
     val gistsUrl: String? = null,

    @field:Json(name = "starred_url")
     val starredUrl: String? = null,

    @field:Json(name = "subscriptions_url")
     val subscriptionsUrl: String? = null,

    @field:Json(name = "organizations_url")
     val organizationsUrl: String? = null,

    @field:Json(name = "repos_url")
     val reposUrl: String? = null,

    @field:Json(name = "events_url")
     val eventsUrl: String? = null,

    @field:Json(name = "received_events_url")
     val receivedEventsUrl: String? = null,

    @field:Json(name = "type")
     val type: String? = null,

    @field:Json(name = "site_admin")
     val siteAdmin: Boolean? = false,

    @field:Json(name = "name")
     val name: String? = null,

    @field:Json(name = "company")
     val company: Any? = null,

    @field:Json(name = "blog")
     val blog: String? = null,

    @field:Json(name = "location")
     val location: String? = null,

    @field:Json(name = "email")
     val email: Any? = null,

    @field:Json(name = "hireable")
     val hireable: Any? = null,

    @field:Json(name = "bio")
     val bio: String? = null,

    @field:Json(name = "public_repos")
     val publicRepos: Int? = null,

    @field:Json(name = "public_gists")
     val publicGists: Int? = null,

    @field:Json(name = "followers")
     val followers: Int? = null,

    @field:Json(name = "following")
     val following: Int? = null,

    @field:Json(name = "created_at")
     val createdAt: String? = null,

    @field:Json(name = "updated_at")
     val updatedAt: String? = null
)

