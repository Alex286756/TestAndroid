package ru.kuksov.testtask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kuksov.testtask.dao.BinDao
import ru.kuksov.testtask.entity.Bin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinRepository @Inject constructor(private val binDao: BinDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val binList : LiveData<List<Bin>> = binDao.getAllBins()

    fun addBin(bin: Bin) {
        coroutineScope.launch(Dispatchers.IO) {
            binDao.addBin(bin)
        }
    }

    fun getBinById(id : String) : Bin {
        var result = Bin()
        result.binId = id
        coroutineScope.launch(Dispatchers.IO) {
            result = binDao.getBinById(id)
        }
        return result
    }

    fun deleteBinById(id : String) {
        coroutineScope.launch(Dispatchers.IO) {
            binDao.deleteBin(id)
        }
    }
}