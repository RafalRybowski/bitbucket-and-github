package pl.lodz.apzumi.utils.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private lateinit var retrofitBitbucket: Retrofit
    private lateinit var retrofitGitHub: Retrofit

    fun createBitbucketClient() {
        retrofitBitbucket = Retrofit.Builder()
            .baseUrl("https://api.bitbucket.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createGitHubClient() {
        retrofitGitHub = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getBitbucketClient(): Retrofit {
        return retrofitBitbucket
    }

    fun getGitHubClient(): Retrofit {
        return retrofitGitHub
    }
}