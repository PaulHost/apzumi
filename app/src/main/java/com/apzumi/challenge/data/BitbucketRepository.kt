package com.apzumi.challenge.data

import com.apzumi.challenge.data.model.BitbucketResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface BitbucketRepository {
        @GET("/2.0/repositories?fields=values.name,values.owner,values.description")
        fun getRepositories(): Flowable<BitbucketResponse>
}