package pl.lodz.apzumi.fragments.list

import pl.lodz.apzumi.model.ReposytoryInformation
import kotlin.collections.List

interface ReposytoryList {
    interface View {
        fun setRecyclerView(list: List<ReposytoryInformation>)
        fun unBlockSwitch()
        fun showError(error: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onSwitchChange(checked: Boolean)
    }
}