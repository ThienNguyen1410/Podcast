package com.kotlin.podcasts.model

import java.util.*

data class Episode(
    var guid : String = "",
    var title : String = "",
    var description : String = "",
    var media : String = "",
    var mimeType : String = "",
    var releaseDate : Date = Date(),
    var duration : String = ""
)
