package pl.lodz.apzumi.utils.remote.bitbucket

import retrofit2.Response
import retrofit2.http.GET

interface BitbucketService {

    @GET("/2.0/repositories?fields=values.name,values.owner,values.description")
    suspend fun getRepository(): Response<BitbucketModel>
}