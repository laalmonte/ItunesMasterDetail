package com.viyahe.itunesmasterdetail.datamodel

import com.viyahe.itunesmasterdetail.db.Tracks

data class Result(
    val resultCount: Int,
    val results: List<Results>
)

data class Results(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionViewUrl: String,
    val country: String,
    val currency: String,
    val discCount: Int,
    val discNumber: Int,
    val kind: String,
    val previewUrl: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCensoredName: String,
    val trackCount: Int,
    val trackExplicitness: String,
    val trackId: Int,
    val trackName: String,
    val trackNumber: Int,
    val trackPrice: Double,
    val trackTimeMillis: Long,
    val trackViewUrl: String,
    val wrapperType: String
){
    fun toTrack() : Tracks {
        return Tracks(
            artistId,
            artistName,
            artistViewUrl,
            artworkUrl100,
            artworkUrl30,
            artworkUrl60,
            collectionCensoredName,
            collectionExplicitness,
            collectionId,
            collectionName,
            collectionPrice,
            collectionViewUrl,
            country,
            currency,
            discCount,
            discNumber,
            kind,
            previewUrl,
            primaryGenreName,
            releaseDate,
            trackCensoredName,
            trackCount,
            trackExplicitness,
            trackId,
            trackName,
            trackNumber,
            trackPrice,
            trackTimeMillis,
            trackViewUrl,
            wrapperType
        )
    }
}