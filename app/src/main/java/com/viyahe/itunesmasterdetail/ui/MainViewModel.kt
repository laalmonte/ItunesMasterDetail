package com.viyahe.itunesmasterdetail.ui

import android.os.Handler
import android.os.Looper
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viyahe.itunesmasterdetail.datamodel.Data
import com.viyahe.itunesmasterdetail.db.Tracks
import com.viyahe.itunesmasterdetail.repository.TrackRepository
import kotlinx.coroutines.launch
import com.viyahe.itunesmasterdetail.datamodel.Result

class MainViewModel
@ViewModelInject constructor(
    private val trackRepository: TrackRepository,
) : ViewModel() {

    private val _data = MutableLiveData<Data<Result>>()
    val data: LiveData<Data<Result>>
        get() = _data


    init {

    }
    // api call through the repository
    fun getTracks() =
        viewModelScope.launch {
            _data.postValue(Data.loading(null))
            _data.postValue(trackRepository.getTracks())
        }

    // fetching data in room db
    fun getTracksFromDB() : LiveData<MutableList<Tracks>> = trackRepository.getSaveTracks()

    // clear all data for emptying list
    fun clearData() { trackRepository.clearData() }

}