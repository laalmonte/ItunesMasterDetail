package com.viyahe.itunesmasterdetail.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.viyahe.itunesmasterdetail.db.Tracks

@Dao
interface TrackDao {

    @Query("SELECT * FROM Tracks LIMIT 60")
    fun getSavedTrackFromDao(): LiveData<MutableList<Tracks>>

    @Insert
    fun save(tracks: Tracks)

    @Update
    fun update(tracks: Tracks)

    @Query("DELETE FROM Tracks")
    fun clearAll()
}