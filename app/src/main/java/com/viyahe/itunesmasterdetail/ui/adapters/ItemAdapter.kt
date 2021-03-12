package com.viyahe.itunesmasterdetail.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viyahe.itunesmasterdetail.R
import com.viyahe.itunesmasterdetail.datamodel.TrackObject
import com.viyahe.itunesmasterdetail.extension.loadUrls
import kotlinx.android.synthetic.main.view_item.view.*
import kotlin.properties.Delegates

class ItemAdapter(private val onItemSelectCallback: OnItemSelectCallback) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var oldTrackList = mutableListOf<TrackObject>()
    private lateinit var mContext: Context

    private var trackList: List<TrackObject> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))
    }

    override fun getItemCount(): Int = trackList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(trackList[position], position)

    }

    fun updateContext(contextParam: Context){
        mContext = contextParam
    }

    fun updateTrackList(newDataSet: MutableList<TrackObject>) {
        oldTrackList.clear()
        oldTrackList.addAll(newDataSet)
        trackList = emptyList()
        trackList = oldTrackList
        notifyDataSetChanged()
    }

    fun updateEmptyTrackList() {
        oldTrackList.clear()
        trackList = emptyList()
        trackList = oldTrackList
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(obj: TrackObject, position: Int) {
            var name = ""
            obj.trackName?.let {
                name = "Track Name: " + obj.trackName
            } ?: run {
                name = "Track Name: None"
            }

            val genre = "Genre: " + obj.primaryGenreName
            val prc2 = "Price: $" + obj.trackPrice.toString()
            itemView.tvGenre.text = genre
            itemView.tvName.text  = name
            itemView.tvPrice.text = prc2
            itemView.ivArtwork.loadUrls(mContext, obj.artworkUrl60)

            itemView.trackLayout.setOnClickListener { onItemSelectCallback.onSelectItem(obj) }
        }
    }

    interface OnItemSelectCallback {
        fun onSelectItem(trackObject: TrackObject)
    }

}