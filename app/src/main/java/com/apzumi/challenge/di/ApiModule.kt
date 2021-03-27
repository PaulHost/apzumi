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
    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
                )
            }
            connectTimeout(120, TimeUnit.SECONDS)
            readTimeout(120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }.build()
    }
    factory { provideRetrofit(get(), Bitbucket_URL).create(BitbucketRepository::class.java) }
    factory { provideRetrofit(get(), GitHub_URL).create(GitHubRepository::class.java) }
}

private const val Bitbucket_URL = "https://api.bitbucket.org/"

private const val GitHub_URL = "https://api.github.com/"

private fun provideRetrofit(client: OkHttpClient, url: String) = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(url)
    .client(client)
    .build()
