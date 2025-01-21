package ru.kuksov.testtask

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(): ViewModel() {

    var id by mutableStateOf("")

    fun setNewId(bin : String) {
        id = bin
    }
}