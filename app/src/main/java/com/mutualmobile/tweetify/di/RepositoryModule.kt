package com.mutualmobile.tweetify.di

import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideTweetsRepository(): TweetsRepository {
        return TweetsRepository()
    }
}