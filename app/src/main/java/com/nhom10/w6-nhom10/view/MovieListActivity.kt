package com.team12.android_challenge_w6.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.team12.android_challenge_w6.GRID_LAYOUT
import com.team12.android_challenge_w6.LINEAR_LAYOUT
import com.team12.android_challenge_w6.R
import com.team12.android_challenge_w6.adapter.NowPlayingAdapter
import com.team12.android_challenge_w6.adapter.TopRatedMovieAdapter
import com.team12.android_challenge_w6.databinding.ActivityMovieListBinding
import com.team12.android_challenge_w6.fragment.DetailFragment
import com.team12.android_challenge_w6.fragment.NowPlayingFragment
import com.team12.android_challenge_w6.fragment.TopRatedFragment
import com.team12.android_challenge_w6.movie.Movie
import com.team12.android_challenge_w6.viewmodel.NowPlayingViewModel
import com.team12.android_challenge_w6.viewmodel.TopRatedViewModel


class MovieListActivity : AppCompatActivity() {
    private var currentLayout : Int = LINEAR_LAYOUT
    private lateinit var nowPlayingMovieFragment: NowPlayingFragment
    private lateinit var topRatedMovieFragment: TopRatedFragment
    private lateinit var nowPlayingMovieAdapter: NowPlayingAdapter
    private lateinit var topRatedMovieAdapter: TopRatedMovieAdapter
    private lateinit var binding: ActivityMovieListBinding
    private lateinit var movieDetailFragment: DetailFragment
    private lateinit var menu: Menu
    lateinit var movieViewModel: NowPlayingViewModel
    lateinit var topRatedViewModel: TopRatedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        //ViewModel
        movieViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        movieViewModel.listNowPlaying.observe(this, Observer {
            submitListAdapter(it,isNowPlayingMovieFragment = true)
        })

        //ViewModel
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedViewModel.listTopRated.observe(this, Observer {
            submitListAdapter(it,isNowPlayingMovieFragment = false)
        })

        //Fragment & Adapter
        nowPlayingMovieAdapter = NowPlayingAdapter(this)
        nowPlayingMovieFragment = NowPlayingFragment(nowPlayingMovieAdapter)
        topRatedMovieAdapter = TopRatedMovieAdapter(this)
        topRatedMovieFragment = TopRatedFragment(topRatedMovieAdapter)
        setCurrentFragment(nowPlayingMovieFragment)

        nowPlayingMovieAdapter.listener = object : NowPlayingAdapter.NowPlayingMovieAdapterListener {
            override fun onClickItem(movie: Movie) {
                movieDetailFragment = DetailFragment(movie)
                setCurrentFragment(movieDetailFragment)
            }
        }

        topRatedMovieAdapter.listener= object : TopRatedMovieAdapter.TopRatedMovieAdapterListener {
            override fun onClickItem(movie: Movie) {
                movieDetailFragment = DetailFragment(movie)
                setCurrentFragment(movieDetailFragment)
            }
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)
        binding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_now_playing -> setCurrentFragment(nowPlayingMovieFragment)
                R.id.navigation_top_rated -> setCurrentFragment(topRatedMovieFragment)
            }
            true
        }
        //
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    override fun onStart() {
        super.onStart()
//        movieViewModel.coroutinesGetNowPlaying()
//        movieViewModel.coroutinesGetTopRated()
        movieViewModel.coroutinesGetNowPlaying()
        topRatedViewModel.coroutinesGetTopRated()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_switch, menu)
        this.menu = menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sw_layout -> {
                val icon = if (currentLayout == LINEAR_LAYOUT) R.drawable.ic_grid_off else R.drawable.ic_grid_on
                if (currentLayout == LINEAR_LAYOUT) {
                    nowPlayingMovieFragment.changeLayout(GRID_LAYOUT)
                    topRatedMovieFragment.changeLayout(GRID_LAYOUT)
                    menu[0].icon =
                        ContextCompat.getDrawable(this, icon)
                    currentLayout = GRID_LAYOUT
                } else {
                    nowPlayingMovieFragment.changeLayout(LINEAR_LAYOUT)
                    topRatedMovieFragment.changeLayout(LINEAR_LAYOUT)
                    menu[0].icon =
                        ContextCompat.getDrawable(this, icon)
                    currentLayout = LINEAR_LAYOUT
                }
            }
        }
        return true
    }
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView,fragment)
            addToBackStack(null)
        }

    }
    fun submitListAdapter(listMovie: List<Movie>?,isNowPlayingMovieFragment:Boolean){
        if (isNowPlayingMovieFragment){
            nowPlayingMovieAdapter.submitList(listMovie)
        } else {
            topRatedMovieAdapter.submitList(listMovie)
        }
    }
}