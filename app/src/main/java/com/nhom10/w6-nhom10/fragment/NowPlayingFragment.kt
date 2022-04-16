package com.team12.android_challenge_w6.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team12.android_challenge_w6.GRID_LAYOUT
import com.team12.android_challenge_w6.LINEAR_LAYOUT
import com.team12.android_challenge_w6.R
import com.team12.android_challenge_w6.adapter.NowPlayingAdapter

class NowPlayingFragment(val nowPlayingMovieAdapter: NowPlayingAdapter):Fragment() {

    private lateinit var rcv : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_rated,container,false)
        rcv = view.findViewById<RecyclerView>(R.id.rcTop)
        rcv.layoutManager = LinearLayoutManager(this.context)
        rcv.adapter = nowPlayingMovieAdapter
        return view
    }
    fun changeLayout(viewType:Int){
        when(viewType){
            LINEAR_LAYOUT ->{
                rcv.layoutManager = LinearLayoutManager(this.context)
                nowPlayingMovieAdapter.changeViewType(LINEAR_LAYOUT)
            }
            GRID_LAYOUT ->{
                rcv.layoutManager = GridLayoutManager(this.context,2)
                nowPlayingMovieAdapter.changeViewType(GRID_LAYOUT)
            }
            else ->{
                print("error")
            }
        }
    }
}