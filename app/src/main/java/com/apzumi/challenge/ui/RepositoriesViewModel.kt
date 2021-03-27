package com.apzumi.challenge.ui

import android.arch.lifecycle.ViewModel
import com.apzumi.challenge.data.BitbucketRepository
import com.apzumi.challenge.data.GitHubRepository
import com.apzumi.challenge.data.model.RepositoryModel
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

class RepositoriesViewModel(
    private val bitbucketRepository: BitbucketRepository,
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    fun getRepositories(): Flowable<List<RepositoryModel>> = Flowable.zip(
        bitbucketRepository.getRepositories(),
        gitHubRepository.getRepositories(),
        BiFunction { bitbucket, gitHub ->
            mutableListOf<RepositoryModel>().apply {
                addAll(bitbucket.values?.map {
                    RepositoryModel(
                        isGitHub = false,
                        name = it?.name ?: "",
                        details = it?.description ?: "",
                        user = it?.owner?.username ?: "",
                        avatar = it?.owner?.links?.avatar?.href ?: ""
                    )
                } ?: listOf())
                addAll(gitHub.gitHubResponse?.map {
                    RepositoryModel(
                        isGitHub = true,
                        name = it?.name ?: "",
                        details = it?.description ?: "",
                        user = it?.owner?.login ?: "",
                        avatar = it?.owner?.avatarUrl ?: ""
                    )
                } ?: listOf())
            }
        }
    )
}