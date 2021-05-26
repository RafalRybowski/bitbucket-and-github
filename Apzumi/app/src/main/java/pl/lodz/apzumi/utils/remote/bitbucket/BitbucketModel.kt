package pl.lodz.apzumi.utils.remote.bitbucket

import pl.lodz.apzumi.model.Repo
import pl.lodz.apzumi.model.ReposytoryInformation

data class BitbucketModel(
    val values: List<BitbucketValues>?
) {

    fun toReposytoryInformation(): List<ReposytoryInformation> {
        val list = mutableListOf<ReposytoryInformation>()
        values?.forEach {
            list.add(
                ReposytoryInformation(
                    it.name ?: "",
                    it.owner?.display_name ?: "",
                    it.owner?.links?.avatar?.href ?: "",
                    it.description ?: "",
                    Repo.BITBUCKET
                )
            )
        }
        return list
    }
}

data class BitbucketValues(
    val owner: BitbucketUser?,
    val description: String?,
    val name: String?
)

data class BitbucketUser(
    val display_name: String?,
    val links: BitbucketLinks?
)

data class BitbucketLinks(
    val avatar: BitbucketAvatar?
)

data class BitbucketAvatar(
    val href: String?
)