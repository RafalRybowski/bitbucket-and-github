package pl.lodz.apzumi.utils.remote.github

import pl.lodz.apzumi.model.Repo
import pl.lodz.apzumi.model.ReposytoryInformation

data class GithubModel(
    val name: String?,
    val owner: GithubUser?,
    val description: String?
) {
    fun toReposytoryinformation(): ReposytoryInformation =
        ReposytoryInformation(
            name ?: "",
            owner?.login ?: "",
            owner?.avatar_url ?: "",
            description ?: "",
            Repo.GITHUB
        )
}

data class GithubUser(
    var login: String?,
    var avatar_url: String?
)
