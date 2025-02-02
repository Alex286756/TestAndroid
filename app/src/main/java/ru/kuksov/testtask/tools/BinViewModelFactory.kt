package ru.kuksov.testtask.tools

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kuksov.testtask.BinViewModel

class BinViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val binViewModel = BinViewModel(application)
        return  binViewModel as T
    }
}