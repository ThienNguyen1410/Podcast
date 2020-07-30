package com.kotlin.podcasts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kotlin.podcasts.model.Episode
import com.kotlin.podcasts.model.Podcast
import com.kotlin.podcasts.repository.PodcastRepo
import java.util.*

class PodcastViewModel(application: Application) : AndroidViewModel (application){
    var podcastRepo : PodcastRepo? = null
    var activePodcastViewData: PodcastViewData? = null

data class PodcastViewData (
    var subcribed : Boolean = false,
    var feedTitle : String? = "",
    var feedUrl : String? = "",
    var feedDescription : String? = "",
    var imageUrl : String? = "",
    var episodes : List<EpisodeViewData>
)

data class EpisodeViewData (
    var guid : String? = "",
    var title : String? = "",
    var description : String? = "",
    var mediaUrl : String? = "",
    var releaseDate : Date? = null,
    var duration : String? = ""
    )

    private fun episodesToEpisodesView(episodes: List<Episode>) : List<EpisodeViewData> {
        return episodes.map {
            EpisodeViewData(it.guid,it.title,it.description,it.media,it.releaseDate,it.duration)
        }
    }

    private fun podcastToPodcastView(podcast : Podcast) : PodcastViewData{
        return PodcastViewData(
            false,
            podcast.feedTitle,
            podcast.feedUrl,
            podcast.feedDesc,
            podcast.imageUrl,
            episodesToEpisodesView(podcast.episodes)
        )
    }
}
