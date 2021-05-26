package pl.lodz.apzumi.fragments.descryption

import pl.lodz.apzumi.model.ReposytoryInformation

class DescryptionPresenter(private val view: Descryption.View) : Descryption.Presenter {

    override fun onCreated(reposytoryInformation: ReposytoryInformation) {
        view.setUserName(reposytoryInformation.userName)
        view.setReposytoryName(reposytoryInformation.reposytoryName)
        view.setDescryption(reposytoryInformation.reposytoryDescryption)
        view.setAvatar(reposytoryInformation.avatarUrl)
    }

}