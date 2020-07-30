package com.kotlin.podcasts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kotlin.podcasts.repository.ItunesRepo
import com.kotlin.podcasts.service.PodcastResponse
import com.kotlin.podcasts.util.DateUtils

class SearchViewModel (application : Application) : AndroidViewModel(application) {
    var itunesRepo : ItunesRepo? = null

    data class PodcastSummaryViewData(
        var name:String? = "",
        var lastUpdate:String? = "",
        var imageUrl: String? = "",
        var feedUrl: String? = "")
    private fun itunesPodcastToPodcastSummaryView(
        itunesPodcast: PodcastResponse.ItunesPodcast) : PodcastSummaryViewData {
        return PodcastSummaryViewData(
            itunesPodcast.collectionCensoredName,
            DateUtils.jsonDateToShortDate(itunesPodcast.releaseDate),
            itunesPodcast.artworkUrl30,
            itunesPodcast.feedUrl
        )
    }

     fun searchPodcasts(term : String?,
        callback: (List<PodcastSummaryViewData>) -> Unit) {
        itunesRepo?.searchByTerm(term, {results ->
            if (results == null) {
                callback(emptyList())
            } else {
                val searchView = results.map {
                    itunesPodcastToPodcastSummaryView(it)
                }
                callback(searchView)
            }
        })
    }

}