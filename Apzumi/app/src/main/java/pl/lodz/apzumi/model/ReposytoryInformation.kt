package pl.lodz.apzumi.model

import java.io.Serializable

class ReposytoryInformation(
    val reposytoryName: String,
    val userName: String,
    val avatarUrl: String,
    val reposytoryDescryption: String,
    val repo: Repo
) : Serializable, Comparable<ReposytoryInformation> {

    override fun compareTo(other: ReposytoryInformation): Int {
        return this.reposytoryName.compareTo(other.reposytoryName)
    }
}