package com.kotlin.podcasts.model

import java.util.*

data class Podcast(
    var feedUrl : String = "",
    var feedTitle : String = "",
    var feedDesc : String = "",
    var imageUrl : String = "",
    var lastUpdate : Date = Date(),
    var episodes : List<Episode> = listOf()
)

