package com.viyahe.itunesmasterdetail.ui
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.viyahe.itunesmasterdetail.datamodel.Status
import com.viyahe.itunesmasterdetail.datamodel.TrackObject
import com.viyahe.itunesmasterdetail.extension.hideKeyboard
import com.viyahe.itunesmasterdetail.extension.start
import com.viyahe.itunesmasterdetail.ui.adapters.ItemAdapter
import com.viyahe.itunesmasterdetail.util.SessionManager
import com.viyahe.itunesmasterdetail.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*
import splitties.bundle.putExtras

@AndroidEntryPoint
class MainAcivity : AppCompatActivity(){

    private val mainViewModel : MainViewModel by viewModels()
    private val collectionListStorage = mutableListOf<TrackObject>()
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        collectionListStorage.clear()
        setupAdapter()
        mainViewModel.getTracks()
        subscribeUi()
    }

    // initializing recycler view
    private fun setupAdapter(){
        itemAdapter = ItemAdapter(onItemSelectCallback)
        itemList.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    // subscribing all lifecycle observables
    private fun subscribeUi() {
        mainViewModel.data.observe(this, Observer { response ->
            when(response.status) {
                Status.ERROR -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        progressBarAnimation.visibility = View.GONE
                        itemList.visibility             = View.VISIBLE
                    }, 2000)

                    if (response.message.toString().contains("itunes")){
                        Toast.makeText(this, "Please check network connection and try again...", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,response.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                    subscribeDB()
                } Status.LOADING -> {}
                else -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        progressBarAnimation.visibility = View.GONE
                        itemList.visibility             = View.VISIBLE
                    }, 2000)

                    subscribeDB()
                }
            }
        })

    }

    // accessing db
    private fun subscribeDB(){
        mainViewModel.getTracksFromDB().observe(this, Observer {
            if (it.size > 0) {
                it.forEach { col ->
                    collectionListStorage.add(
                        TrackObject(
                            col.artistId,
                            col.artistName,
                            col.artistViewUrl,
                            col.artworkUrl100,
                            col.artworkUrl30,
                            col.artworkUrl60,
                            col.collectionCensoredName,
                            col.collectionExplicitness,
                            col.collectionId,
                            col.collectionName,
                            col.collectionPrice,
                            col.collectionViewUrl,
                            col.country,
                            col.currency,
                            col.discCount,
                            col.discNumber,
                            col.kind,
                            col.previewUrl,
                            col.primaryGenreName,
                            col.releaseDate,
                            col.trackCensoredName,
                            col.trackCount,
                            col.trackExplicitness,
                            col.trackId,
                            col.trackName,
                            col.trackNumber,
                            col.trackPrice,
                            col.trackTimeMillis,
                            col.trackViewUrl,
                            col.wrapperType,
                    )
                    )
                }
            } else {
                collectionListStorage.clear()
            }

            itemAdapter.updateTrackList(collectionListStorage)
            itemAdapter.updateContext(this)

        })
    }

    // call back on recycler
    private val onItemSelectCallback = object : ItemAdapter.OnItemSelectCallback {
        override fun onSelectItem(trackObject: TrackObject) {
            goToDetails(trackObject)
        }
    }


    // page transition
    private fun goToDetails(collectionObject: TrackObject){
        val trackNullable: TrackObject? = collectionObject
        start<DetailActivity>{
            putExtras(DetailActivity.ExtraSpec) {
                trck = trackNullable
            }
        }
    }

    // settings menu ui
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    // settings menu functionalities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                Toast.makeText(applicationContext, "Thank you for using the App, Goodbye", Toast.LENGTH_LONG).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    finish()
                }, 1250)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // holding main page
    override fun onBackPressed() {
        super.onBackPressed()
    }


}