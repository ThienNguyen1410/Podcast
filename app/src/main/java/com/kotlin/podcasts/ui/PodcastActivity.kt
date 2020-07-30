package com.kotlin.podcasts.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.podcasts.R
import com.kotlin.podcasts.adapter.PodcastListAdapter
import com.kotlin.podcasts.repository.ItunesRepo
import com.kotlin.podcasts.service.ItunesService
import com.kotlin.podcasts.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class PodcastActivity : AppCompatActivity(),PodcastListAdapter.PodcastListAdapterListener {
    private val TAG = javaClass.simpleName
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var podcastListAdapter : PodcastListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolBar()
        setViewModel()
        updateControls()
        handlerIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        val searchMenuItem = menu?.findItem(R.id.search_item)
        val searchView = searchMenuItem?.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return true

    }

    private fun performSearch(term: String?) {
        showProgessBar()
        searchViewModel.searchPodcasts(term) {results ->
            hideProgressBar()
            Log.i(TAG,"Result = $results")
            toolbar.title = getString(R.string.search_results)
            podcastListAdapter.setSearchData(results)
        }
    }

    private fun handlerIntent(intent : Intent?) {
        if(Intent.ACTION_SEARCH == intent?.action) {
          val query = intent.getStringExtra(SearchManager.QUERY)
                performSearch(query)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handlerIntent(intent)
    }
    private fun setToolBar(){
        setSupportActionBar(toolbar)
    }

    private fun setViewModel() {
        val service = ItunesService.instance
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.itunesRepo = ItunesRepo(service)
    }

    private fun updateControls(){
        podcastRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        podcastRecyclerView.layoutManager = layoutManager
        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(
            podcastRecyclerView.context, layoutManager.orientation)
        podcastRecyclerView.addItemDecoration(dividerItemDecoration)
        podcastListAdapter = PodcastListAdapter(null, this,this)
        podcastRecyclerView.adapter = podcastListAdapter
    }

    override fun onShowDetail(podcastSummaryViewData: SearchViewModel.PodcastSummaryViewData) {
        TODO("Not yet implemented")
    }

    private fun showProgessBar() {
        progressBar.visibility = View.VISIBLE


    }
    private fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }
}