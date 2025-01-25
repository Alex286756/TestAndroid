package ru.kuksov.testtask.dataload

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kuksov.testtask.entity.Bin
import javax.inject.Inject


class RetrofitHelper @Inject constructor() {
    private val baseUrl = "https://lookup.binlist.net/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun bodyToBin(id: String, body: BinResponse) : Bin {
        val resultBin = Bin()
        resultBin.binId = id
        resultBin.numberLength = body.number?.get("length")?.toString()?.toInt()
        resultBin.numberLuhn = body.number?.get("luhn")?.toString()?.toBoolean()
        resultBin.binScheme = body.scheme
        resultBin.binType = body.type
        resultBin.binBrand = body.brand
        resultBin.binPrepaid = body.prepaid
        resultBin.countryNumeric = body.country?.get("numeric")?.toString()
        resultBin.countryAlpha2 = body.country?.get("alpha2")?.toString()
        resultBin.countryName = body.country?.get("name")?.toString()
        resultBin.countryEmoji = body.country?.get("emoji")?.toString()
        resultBin.countryCurrency = body.country?.get("currency")?.toString()
        resultBin.countryLatitude = body.country?.get("latitude")?.toString()?.toDouble()
        resultBin.countryLongitude = body.country?.get("longitude")?.toString()?.toDouble()
        resultBin.bankName = body.bank?.get("name")?.toString()
        resultBin.bankUrl = body.bank?.get("url")?.toString()
        resultBin.bankPhone = body.bank?.get("phone")?.toString()
        resultBin.bankCity = body.bank?.get("city")?.toString()
        return resultBin
    }

}