package ru.kuksov.testtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kuksov.testtask.dao.BinDao
import ru.kuksov.testtask.entity.Bin

@Database(entities = [(Bin::class)], version = 2)
abstract class BinDb: RoomDatabase() {

    abstract fun BinDao(): BinDao

    companion object {
        private var INSTANCE: BinDb? = null
        fun getInstance(context: Context): BinDb {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BinDb::class.java,
                        "bindb"

                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}