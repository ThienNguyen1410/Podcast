package com.kotlin.podcasts.repository

import com.kotlin.podcasts.service.ItunesService
import com.kotlin.podcasts.service.PodcastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItunesRepo (private val itunesService: ItunesService) {

    fun searchByTerm(term: String?,
    callBack: (List<PodcastResponse.ItunesPodcast>?) -> Unit) {
        val postcastCall = itunesService.searchPodcastByTerm(term)
        postcastCall.enqueue(object: Callback<PodcastResponse> {
            override fun onFailure(call: Call<PodcastResponse>?, t: Throwable) {
                callBack(null)
                print("Failed get podcast in PodcastRepo")
            }
            override fun onResponse(
                call: Call<PodcastResponse>?,
                response: Response<PodcastResponse>?
            ) {
                val body = response?.body()
                callBack(body?.results)
            }
        })
    }
}