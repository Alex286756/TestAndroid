package ru.kuksov.testtask

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class BinViewModel @Inject constructor() : ViewModel() {
    var id by mutableStateOf("")
    var data by mutableStateOf(HashMap<String, String>())
    
    fun feelData(bin : String) {
        data.set("binNumber", "binNumber")
        data.set("binScheme", "binScheme")
        data.set("binType", "binType")
        data.set("binBrand", "binBrand")
        data.set("binBankCountryNumeric", "binBankCountryNumeric")
        data.set("binBankCountryAlpha2", "binBankCountryAlpha2")
        data.set("binBankCountryName", "binBankCountryName")
        data.set("binBankCountryEmoji", "binBankCountryEmoji")
        data.set("binBankCountryCurrency", "binBankCountryCurrency")
        data.set("binBankCountryLatitude", "binBankCountryLatitude")
        data.set("binBankCountryLongitude", "binBankCountryLongitude")
        data.set("binBankName", "binBankName")
    }

    fun setNewId(bin : String) {
        id = bin
        feelData(bin)
    }
}