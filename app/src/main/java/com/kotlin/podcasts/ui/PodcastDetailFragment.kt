package com.kotlin.podcasts.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kotlin.podcasts.R
import com.kotlin.podcasts.viewmodel.PodcastViewModel
import kotlinx.android.synthetic.main.fragment_podcast_detail.*

class PodcastDetailFragment : Fragment() {
    private lateinit var podcastViewModel : PodcastViewModel
    companion object {
        fun newInstance() : PodcastDetailFragment {
            return  PodcastDetailFragment()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_podcast_detail,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateControl()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_details, menu)
    }

    private fun setupViewModel() {
        activity?.let {
            podcastViewModel = ViewModelProvider(it).get(PodcastViewModel::class.java)
        }
    }

    private fun updateControl() {
        val viewData = podcastViewModel.activePodcastViewData ?: return
        feedTiltleTextView.text = viewData.feedTitle
        feedDescTextView.text = viewData.feedDescription
        activity?.let {
            Glide.with(it).load(viewData.imageUrl).into(imageView)
        }

    }
}