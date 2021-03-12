package com.viyahe.itunesmasterdetail.module

import android.content.Context
import androidx.room.Room
import com.viyahe.itunesmasterdetail.BuildConfig
import com.viyahe.itunesmasterdetail.db.AppDb
import com.viyahe.itunesmasterdetail.db.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDb =
        buildDatabase(appContext)

    @Provides
    fun provideMovieDao(appDatabase: AppDb): TrackDao = appDatabase.trackDao()


    private fun buildDatabase(appContext: Context): AppDb {
        val builder = Room.databaseBuilder(
            appContext.applicationContext,
            AppDb::class.java, BuildConfig.DB_NAME
        )
        builder.allowMainThreadQueries()
        return builder.build()
    }
}