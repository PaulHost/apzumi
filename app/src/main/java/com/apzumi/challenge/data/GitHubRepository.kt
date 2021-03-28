package com.apzumi.challenge.data

import com.apzumi.challenge.data.model.GitHubResponseItem
import io.reactivex.Flowable
import retrofit2.http.GET

interface GitHubRepository {
    @GET("/repositories?since=25")
    fun getRepositories(): Flowable<List<GitHubResponseItem>>
}