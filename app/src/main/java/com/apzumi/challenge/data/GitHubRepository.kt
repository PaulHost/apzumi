package com.apzumi.challenge.data

import com.apzumi.challenge.data.model.GitHubResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface GitHubRepository {
        @GET("/repositories")
        fun getRepositories(): Flowable<GitHubResponse>
}