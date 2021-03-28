package com.apzumi.challenge.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.apzumi.challenge.common.applySchedulers
import com.apzumi.challenge.data.BitbucketRepository
import com.apzumi.challenge.data.GitHubRepository
import com.apzumi.challenge.data.model.RepositoryModel
import com.apzumi.challenge.data.model.mapToRepositoryModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class RepositoriesViewModel(
    private val bitbucketRepository: BitbucketRepository,
    private val gitHubRepository: GitHubRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val repositoriesLiveData = MutableLiveData<List<RepositoryModel>>()

    fun getRepositories(): Disposable = Flowable.zip(
        bitbucketRepository.getRepositories(),
        gitHubRepository.getRepositories()
    ) { bitbucket, gitHub ->
        mutableListOf<RepositoryModel>().apply {
            addAll(bitbucket.mapToRepositoryModel())
            addAll(gitHub.mapToRepositoryModel())
        }
    }
        .applySchedulers()
        .doOnNext {
            Timber.e("$it")
        }
        .subscribe(repositoriesLiveData::setValue, Timber::e)
        .addToCompositeDisposable()

    private fun Disposable.addToCompositeDisposable(): Disposable = apply {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
