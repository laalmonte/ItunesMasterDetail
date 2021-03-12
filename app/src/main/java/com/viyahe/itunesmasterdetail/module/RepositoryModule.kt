package com.viyahe.itunesmasterdetail.module

import com.viyahe.itunesmasterdetail.api.TrackService
import com.viyahe.itunesmasterdetail.db.dao.TrackDao
import com.viyahe.itunesmasterdetail.repository.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTrackRepository(
        trackDao: TrackDao,
        trackservice: TrackService
    ): TrackRepository =
        TrackRepository(trackDao, trackservice)

}