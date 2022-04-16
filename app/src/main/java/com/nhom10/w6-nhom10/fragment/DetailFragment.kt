package com.team12.android_challenge_w6.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.team12.android_challenge_w6.R
import com.team12.android_challenge_w6.movie.Movie

class DetailFragment(val movie: Movie):Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail,container,false)
        val picturePath = "https://image.tmdb.org/t/p/w500"

        view.findViewById<TextView>(R.id.tv_movie_name).text = movie.title
        view.findViewById<TextView>(R.id.tv_overview_content)?.text = movie.overview
        view.findViewById<TextView>(R.id.tv_popularity_value)?.text = movie.popularity.toString()
        view.findViewById<TextView>(R.id.tv_release_date).text = movie.releaseDate
        view.findViewById<TextView>(R.id.tv_average_vote_value).text = "(${movie.voteAverage}/10)"
        view.findViewById<TextView>(R.id.txtVoteCountValue).text = movie.voteCount.toString()
        Glide.with(this.requireContext()).load(picturePath + movie.backdropPath).into(view.findViewById<ImageView>(R.id.img_backdrop_movie))
        Glide.with(this.requireContext()).load(picturePath + movie.posterPath).into(view.findViewById<ImageView>(R.id.img_poster_movie))
        return view
    }
}