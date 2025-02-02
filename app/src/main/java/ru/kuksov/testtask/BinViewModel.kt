package ru.kuksov.testtask

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kuksov.testtask.dao.BinDao
import ru.kuksov.testtask.database.BinDb
import ru.kuksov.testtask.dataload.BinApi
import ru.kuksov.testtask.dataload.RetrofitHelper
import ru.kuksov.testtask.entity.Bin
import ru.kuksov.testtask.repository.BinRepository
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    application: Application,
//    private val binData : RetrofitHelper,
//    private val repository: BinRepository
): ViewModel() {

    val binList: LiveData<List<Bin>>

    @Inject
    lateinit var repository: BinRepository

    var currentBinId by mutableStateOf("")

    init {
        val binDb = BinDb.getInstance(application)
        val binDao = binDb.BinDao()
        repository = BinRepository(binDao)
        binList = repository.binList
    }

    fun changeBinId(value: String){
        currentBinId = value
    }

//    fun changeAge(value: String){
//        userAge = value.toIntOrNull()?:userAge
//    }
//    fun addUser() {
//        repository.addUser(User(userName, userAge))
//    }
//    fun deleteUser(id: Int) {
//        repository.deleteUser(id)
//    }




    val responseData = MutableLiveData<Bin>()

//    val binList = MutableLiveData<List<Bin>>()// = binDao.getAllBins()

//    var id = MutableLiveData<String>()

    private var job: Job? = null

//    fun getBinList() {
//        viewModelScope.launch {
//            val allBins = binDao.getAllBins()
//            withContext(Dispatchers.Main) {
//                binList.postValue(allBins.value)
//            }
//        }
//    }

    fun getDataFromAPI(url: String) {
//        val binsApi = binData.getInstance().create(BinApi::class.java)
//
//        viewModelScope.launch {
//            val resultApi = binsApi.getBin(url)
//            withContext(Dispatchers.Main) {
//                if (resultApi.isSuccessful)
//                    responseData.postValue(binData.bodyToBin(url, resultApi.body()!!))
//            }
//        }
    }

//    fun setNewId(bin : String) {
//        id.value = bin
//    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}