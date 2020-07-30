package com.kotlin.podcasts.service

import java.util.*

data class PodcastResponse(
    val resultsInt: Int,
    val results: List<ItunesPodcast>) {

    data class ItunesPodcast(
        val collectionCensoredName: String,
        val feedUrl: String,
        val artworkUrl30: String,
        val releaseDate: String
    )
}