package pl.lodz.apzumi.utils.remote.github

import retrofit2.Response
import retrofit2.http.GET

interface GithubService {

    @GET("/repositories")
    suspend fun getRepository(): Response<List<GithubModel>>

}