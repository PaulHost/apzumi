package com.apzumi.challenge.data.model

data class RepositoryModel(
    val isGitHub: Boolean = false,
    val name: String = "",
    val avatar: String = "",
    val user: String = "",
    val details: String = ""
)

fun BitbucketResponse.mapToRepositoryModel() = values?.map {
    RepositoryModel(
        isGitHub = false,
        name = it?.name ?: "",
        details = it?.description ?: "",
        user = it?.owner?.username ?: "",
        avatar = it?.owner?.links?.avatar?.href ?: ""
    )
} ?: listOf()

fun List<GitHubResponseItem>.mapToRepositoryModel() = map {
    RepositoryModel(
        isGitHub = true,
        name = it.name ?: "",
        details = it.description ?: "",
        user = it.owner?.login ?: "",
        avatar = it.owner?.avatarUrl ?: ""
    )
}