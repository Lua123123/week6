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
import com.team12.android_challenge_w6.adapter.TopRatedMovieAdapter

class TopRatedFragment(val topRatedMovieAdapter: TopRatedMovieAdapter):Fragment(){
    private lateinit var rcList : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_now_playing,container,false)
        rcList = view.findViewById<RecyclerView>(R.id.rcFavorite)
        rcList.layoutManager = LinearLayoutManager(this.context)
        rcList.adapter = topRatedMovieAdapter
        return view
    }
    fun changeLayout(viewType:Int){
        when(viewType){
            LINEAR_LAYOUT ->{
                rcList.layoutManager = LinearLayoutManager(this.context)
                topRatedMovieAdapter.changeViewType(LINEAR_LAYOUT)
            }
            GRID_LAYOUT ->{
                rcList.layoutManager = GridLayoutManager(this.context,2)
                topRatedMovieAdapter.changeViewType(GRID_LAYOUT)
            }
            else ->{
                print("error")
            }
        }
    }
}