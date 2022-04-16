package com.team12.android_challenge_w6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team12.android_challenge_w6.movie.Movie
import com.team12.android_challenge_w6.network.ClientRetrofit
import kotlinx.coroutines.*

class NowPlayingViewModel:ViewModel() {

    //COROUTINES
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    var listNowPlaying =  MutableLiveData<List<Movie>?>()

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun coroutinesGetNowPlaying() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            getNowPlaying()
        }
    }

    fun getNowPlaying(){
        viewModelScope.launch {
            val movieResponse = ClientRetrofit.getInstance()
                    .API.listNowPlayMovies(
                            language = "en-US",
                            page = 1
                    )
            listNowPlaying.postValue(movieResponse.results!!)
        }
    }

}