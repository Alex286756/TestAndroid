package ru.kuksov.testtask.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.kuksov.testtask.entity.Bin

@Dao
interface BinDao {
    @Query("SELECT * FROM bins")
    fun getAllBins(): LiveData<List<Bin>>

    @Query("SELECT * FROM bins WHERE binId = :binId")
    fun getBinById(binId: String): Bin

    @Insert
    fun addBin(user: Bin)

    @Query("DELETE FROM bins WHERE binId = :binId")
    fun deleteBin(binId: String)
}