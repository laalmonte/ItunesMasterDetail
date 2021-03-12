package com.viyahe.itunesmasterdetail.ui

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.viyahe.itunesmasterdetail.datamodel.TrackObject
import com.viyahe.itunesmasterdetail.util.DateHelper
import com.viyahe.itunesmasterdetail.util.SessionManager
import com.viyahe.itunesmasterdetail.R
import com.viyahe.itunesmasterdetail.extension.loadUrls
import kotlinx.android.synthetic.main.activity_detail.*
import splitties.bundle.BundleSpec
import splitties.bundle.withExtras
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class DetailActivity : AppCompatActivity() {

    private var track: TrackObject? = null
    private var mContext: Context = this
    private var dateHelper = DateHelper()

    private lateinit var artistUrl: String
    private lateinit var collectiontUrl: String
    private lateinit var trackUrl: String

    object ExtraSpec : BundleSpec() {
        var trck: TrackObject? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initData()
        attachActions()
    }

    private fun initData(){
        withExtras(ExtraSpec) {
            track = trck

            track?.let {
                ivLargeImage.loadUrls(mContext, it.artworkUrl100)

                val genre                   = "Genre: " + it.primaryGenreName
                tvGenre.text                = genre
                tvName.text                 = it.trackName
                tvArtistName.text           = it.artistName
                tvCollectionName.text       = it.collectionName
                tvName.paintFlags           = Paint.UNDERLINE_TEXT_FLAG
                tvArtistName.paintFlags     = Paint.UNDERLINE_TEXT_FLAG
                tvCollectionName.paintFlags = Paint.UNDERLINE_TEXT_FLAG

                trackUrl                    = it.trackViewUrl.toString()
                artistUrl                   = it.trackViewUrl.toString()
                collectiontUrl              = it.collectionViewUrl.toString()

                it.trackTimeMillis?.let { mills ->
                    val time    = "Track Time: " + TimeUnit.MILLISECONDS.toMinutes(mills) + " minutes"
                    tvTrackTime.text      = time
                }

                // '2006-01-01T08:00:00Z'
                // MMMM dd, YYYY
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("MMMM dd, yyyy")
                    val dateConverted = formatter.format(parser.parse(it.releaseDate))
                    val release = "Release Date: " + dateConverted.toString()
                    tvReleaseDate.text = release
                } else {
                    val release2 = "Release Date: " + it.releaseDate
                    tvReleaseDate.text = release2
                }

                when (it.currency) {
                    "USD" -> {
                        val prc    = "Price: $ " + it.trackPrice.toString()
                        tvPrice.text = prc }
                    "PHP" -> {
                        val prc2    = "Price: P " + it.trackPrice.toString()
                        tvPrice.text = prc2 }
                    else -> {
                        val prc3    = "Price: " + it.trackPrice.toString()
                        tvPrice.text = prc3 }
                }
            }
        }
    }

    private fun attachActions() {
        tvArtistName.setOnClickListener {
            getUrlFromIntent(it, artistUrl)
        }

        tvName.setOnClickListener {
            getUrlFromIntent(it, trackUrl)
        }

        tvCollectionName.setOnClickListener {
            getUrlFromIntent(it, collectiontUrl)
        }

        tvBackToList.setOnClickListener {
            finish()
        }
    }

    private fun getUrlFromIntent(view: View, urlIntent:String ) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlIntent)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        finish()
    }
}