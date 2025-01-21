package ru.kuksov.testtask

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kuksov.testtask.dao.BinDao
import ru.kuksov.testtask.database.BinDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideBinDb(application: Application) : BinDb {
        return BinDb.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideBinDao(binDb : BinDb) : BinDao {
        return binDb.BinDao()
    }

}