package pl.lodz.apzumi.fragments.descryption

import pl.lodz.apzumi.model.ReposytoryInformation

interface Descryption {
    interface View {
        fun setUserName(name: String)
        fun setReposytoryName(name: String)
        fun setDescryption(descryption: String)
        fun setAvatar(url: String)
    }

    interface Presenter {
        fun onCreated(reposytoryInformation: ReposytoryInformation)
    }
}