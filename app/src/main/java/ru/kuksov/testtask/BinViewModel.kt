package ru.kuksov.testtask

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kuksov.testtask.dataload.BinApi
import ru.kuksov.testtask.dataload.RetrofitHelper
import ru.kuksov.testtask.entity.Bin
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val binData : RetrofitHelper
): ViewModel() {

    val responseData = MutableLiveData<Bin>()

    var id by mutableStateOf("")

    var job: Job? = null

    fun getDataFromAPI(url: String) {
        val binsApi = binData.getInstance().create(BinApi::class.java)

        viewModelScope.launch {
            val resultApi = binsApi.getBin(url)
            withContext(Dispatchers.Main) {
                if (resultApi.isSuccessful)
                    responseData.postValue(binData.bodyToBin(url, resultApi.body()!!))
            }
        }
    }

    fun setNewId(bin : String) {
        id = bin
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}