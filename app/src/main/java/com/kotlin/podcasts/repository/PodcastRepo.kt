package com.kotlin.podcasts.repository

import com.kotlin.podcasts.model.Podcast

class PodcastRepo {
    fun getPodcast(feedUrl:String,
    callback: (Podcast?) -> Unit ) {
        callback(Podcast(feedUrl,"No name","No Description","No Image"))
    }
}