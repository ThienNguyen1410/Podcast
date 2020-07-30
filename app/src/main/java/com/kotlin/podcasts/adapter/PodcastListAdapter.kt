package com.kotlin.podcasts.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.podcasts.R
import com.kotlin.podcasts.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.search_items.view.*

class PodcastListAdapter(
    private var podcastSummaryViewList : List<SearchViewModel.PodcastSummaryViewData>?,
    private val podcastListAdapterListener : PodcastListAdapterListener,
    private val parentActivity: Activity
    ): RecyclerView.Adapter<PodcastListAdapter.ViewHolder>(){

 interface PodcastListAdapterListener {
     fun onShowDetail(podcastSummaryViewData: SearchViewModel.PodcastSummaryViewData)
 }
    inner class ViewHolder(
        v: View,
        private val podcastListAdapterListener: PodcastListAdapterListener): RecyclerView.ViewHolder(v) {
        var podcastSummaryViewData : SearchViewModel.PodcastSummaryViewData? = null
        val nameTextView :TextView= v.findViewById(R.id.podcastName)
        val lastedUpdateTextView: TextView = v.findViewById(R.id.lastUpdate)
        val podcastImage: ImageView = v.findViewById(R.id.podcastImage)

        init {
            v.setOnClickListener {
                podcastSummaryViewData?.let {
                    podcastListAdapterListener.onShowDetail(it)
                }
            }
        }
    }

    fun setSearchData(podcastSummaryViewData:List<SearchViewModel.PodcastSummaryViewData>) {
        podcastSummaryViewList = podcastSummaryViewData
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_items, parent,false),
        podcastListAdapterListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchViewList = podcastSummaryViewList ?: return
        val searchView = searchViewList[position]
        holder.podcastSummaryViewData = searchView
        holder.nameTextView.text = searchView.name
        holder.lastedUpdateTextView.text = searchView.lastUpdate
        Glide.with(parentActivity).load(searchView.imageUrl).into(holder.podcastImage)

    }

    override fun getItemCount(): Int {
        return podcastSummaryViewList?.size ?: 0
    }
}
