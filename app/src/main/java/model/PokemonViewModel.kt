package model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class PokemonViewModel : ViewModel() {
    val data: MutableLiveData<PokemonBean?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()
    private val threadRunning: MutableLiveData<Boolean> = MutableLiveData(false)

    fun loadData() {
        errorMessage.postValue(null)
        data.postValue(null)
        threadRunning.postValue(true)
        thread {
            try {
                data.postValue(RequestUtils.loadWeather("ditto"))
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message)
            }
            threadRunning.postValue(false)
        }
    }

}