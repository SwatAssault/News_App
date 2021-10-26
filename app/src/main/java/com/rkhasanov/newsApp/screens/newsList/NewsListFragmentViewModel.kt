package com.rkhasanov.newsApp.screens.newsList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkhasanov.newsApp.model.newsRequest.NewsRequester
import com.rkhasanov.newsApp.model.pojo.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class NewsListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var requestResult = MutableLiveData<RequestResult>()

    private var newsRequester = NewsRequester()

    fun getRequestResult(): MutableLiveData<RequestResult> {
        return requestResult
    }

    fun fetch(title: String = "Android", onSuccess: (itemsCount: Int) -> Unit) {
        val list = listOf(
            "android",
            "microsoft",
            "airplane",
            "warming",
            "earth",
            "minecraft",
            "medicine",
            "eminem",
            "video",
            "concert"
        )
        newsRequester.execute(list.random()) { response ->
            requestResult.postValue(response)
            onSuccess(response?.totalResults!!)
        }



    }

}
