package pl.lodz.apzumi.fragments.list

import android.net.ConnectivityManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.lodz.apzumi.model.ReposytoryInformation
import pl.lodz.apzumi.utils.remote.RetrofitClient
import pl.lodz.apzumi.utils.remote.bitbucket.BitbucketService
import pl.lodz.apzumi.utils.remote.bitbucket.BitbucketValues
import pl.lodz.apzumi.utils.remote.github.GithubService
import java.util.*
import kotlin.coroutines.CoroutineContext

class ReposytoryListPresenter(
    private val view: ReposytoryList.View,
    private val connectivityManager: ConnectivityManager
) : ReposytoryList.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    private val serviceBitbucker by lazy {
        RetrofitClient.getBitbucketClient().create(BitbucketService::class.java)
    }

    private val serviceGithub by lazy {
        RetrofitClient.getGitHubClient().create(GithubService::class.java)
    }

    override fun onViewCreated() {
        if (checkIfInternetConnected()) {
            launch {
                val repo = getRepository()
                withContext(Dispatchers.Main) {
                    view.setRecyclerView(repo)
                }
                view.unBlockSwitch()
            }
        } else {
            view.showError("Internet not connected")
        }
    }

    override fun onSwitchChange(checked: Boolean) {
        if (checkIfInternetConnected()) {
            launch {
                val repo = getRepository()
                if (checked) {
                    repo.sort()
                    setList(repo)
                } else {
                    setList(repo)
                }
            }
        } else {
            view.showError("Internet not connected")
        }
    }

    private suspend fun getRepository(): MutableList<ReposytoryInformation> {
        val responseBitbucket = serviceBitbucker.getRepository()
        val repo = mutableListOf<ReposytoryInformation>()
        if (responseBitbucket.isSuccessful) {
            val bitbucketValues: List<ReposytoryInformation>? =
                responseBitbucket.body()?.toReposytoryInformation()
            bitbucketValues?.let {
                repo.addAll(it)
            }
        } else {
            showError("Can not download data from bitbucket ${responseBitbucket.errorBody()}")
        }
        val responseGithub = serviceGithub.getRepository()
        if (responseGithub.isSuccessful) {
            responseGithub.body()?.forEach {
                repo.add(it.toReposytoryinformation())
            }
        } else {
            showError("Can not download data from github ${responseGithub.errorBody()}")
        }
        return repo
    }

    private suspend fun setList(repo: List<ReposytoryInformation>) {
        withContext(Dispatchers.Main) {
            view.setRecyclerView(repo)
        }
    }

    private suspend fun showError(text: String) {
        withContext(Dispatchers.Main) {
            view.showError(text)
        }
    }

    private fun checkIfInternetConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        val result: Boolean? = activeNetwork?.isConnected
        return !(result == null || result == false)
    }
}