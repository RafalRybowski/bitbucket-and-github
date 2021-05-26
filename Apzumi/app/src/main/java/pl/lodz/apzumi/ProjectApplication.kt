package pl.lodz.apzumi

import android.app.Application
import pl.lodz.apzumi.utils.remote.RetrofitClient

class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.createBitbucketClient()
        RetrofitClient.createGitHubClient()
    }

}
