package com.viyahe.itunesmasterdetail.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.viyahe.itunesmasterdetail.api.TrackService
import com.viyahe.itunesmasterdetail.datamodel.Data
import com.viyahe.itunesmasterdetail.datamodel.NoData
import com.viyahe.itunesmasterdetail.datamodel.Result
import com.viyahe.itunesmasterdetail.db.Tracks
import com.viyahe.itunesmasterdetail.db.dao.TrackDao
import javax.inject.Inject

class TrackRepository
@Inject constructor(
    private val trackDao: TrackDao,
    private val apiService: TrackService
) {

    suspend fun getTracks() : Data<Result> {

        try {
            val response = apiService.getTrackFromApi()
            Log.d("API1", " resp = " + response.toString())
            if (response.isSuccessful) {
                response.body()?.let {
                    clearData()
                    Log.d("API1", " body = " + it.toString())
                    it.results.forEach { result ->
                        trackDao.save(result.toTrack())
                    }
                    return Data.success(it)
                } ?: throw NoData()
            } else {
                return Data.error(response.errorBody().toString(), null)
            }
        } catch (e: Exception) {
            return Data.error(e.message.toString(), null)
        }
    }


    fun getSaveTracks(): LiveData<MutableList<Tracks>> = trackDao.getSavedTrackFromDao()

    fun clearData(){
        trackDao.clearAll()
    }

}