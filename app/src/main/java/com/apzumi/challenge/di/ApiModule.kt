package com.apzumi.challenge.di

import com.apzumi.challenge.BuildConfig
import com.apzumi.challenge.data.BitbucketRepository
import com.apzumi.challenge.data.GitHubRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { provideClient() }
    single { provideBitbucket(get()) }
    single { provideGitHub(get()) }
    single { createBitbucketApiInstance<BitbucketRepository>(get()) }
    single { createGitHubApiInstance<GitHubRepository>(get()) }
}

private typealias GitHub = Retrofit

private typealias Bitbucket = Retrofit

private const val Bitbucket_URL = "https://api.bitbucket.org/"

private const val GitHub_URL = "https://api.github.com/"

private fun provideClient() = OkHttpClient.Builder().apply {
    if (BuildConfig.DEBUG) {
        addInterceptor(
            HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        )
    }
    connectTimeout(120, TimeUnit.SECONDS)
    readTimeout(120, TimeUnit.SECONDS)
    writeTimeout(120, TimeUnit.SECONDS)
}.build()

private fun provideRetrofit(client: OkHttpClient, url: String) = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(url)
    .client(client)
    .build()

private fun provideGitHub(client: OkHttpClient): GitHub = provideRetrofit(client, GitHub_URL)
private fun provideBitbucket(client: OkHttpClient): Bitbucket = provideRetrofit(client, Bitbucket_URL)

inline fun <reified T> createGitHubApiInstance(gitHub: GitHub): T = createApiInstance(gitHub)
inline fun <reified T> createBitbucketApiInstance(bitbucket: Bitbucket): T = createApiInstance(bitbucket)

inline fun <reified T> createApiInstance(retrofit: Retrofit): T = retrofit.create(T::class.java)
