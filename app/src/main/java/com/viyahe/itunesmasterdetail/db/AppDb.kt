package com.viyahe.itunesmasterdetail.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viyahe.itunesmasterdetail.db.dao.TrackDao

@Database(
    entities = [Tracks::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun trackDao(): TrackDao

}