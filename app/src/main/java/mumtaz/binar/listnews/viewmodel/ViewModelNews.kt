package mumtaz.binar.listnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mumtaz.binar.listnews.model.ResponseDataNewsItem
import mumtaz.binar.listnews.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelNews : ViewModel(){

    lateinit var liveDataNews : MutableLiveData<List<ResponseDataNewsItem>>

    init {
        liveDataNews = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<ResponseDataNewsItem>>{
        return liveDataNews
    }

    fun getApiNews(){
        RetrofitClient.instance.getAllNews()
            .enqueue(object : Callback<List<ResponseDataNewsItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataNewsItem>>,
                    response: Response<List<ResponseDataNewsItem>>
                ) {
                    if (response.isSuccessful) {
                        liveDataNews.postValue(response.body())
                    }else{
                        liveDataNews.postValue(null)
                    }

                }

                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                    liveDataNews.postValue(null)
                }

            })
    }
}