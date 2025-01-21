package ru.kuksov.testtask.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bins")
class Bin {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var binId : String? = null
    var numberLength : Int? = null
    var numberLuhn : Boolean? = null
    var binScheme : String? = null
    var binType : String? = null
    var binBrand : String? = null
    var binPrepaid : Boolean? = null
    var countryNumeric : String? = null
    var countryAlpha2 : String? = null
    var countryName : String? = null
    var countryEmoji : String? = null
    var countryCurrency : String? = null
    var countryLatitude : Double? = null
    var countryLongitude : Double? = null
    var bankName : String? = null
    var bankUrl : String? = null
    var bankPhone : String? = null
    var bankCity : String? = null

    fun getValues() : HashMap<String, String> {
        val data = HashMap<String, String>()
        data["BIN"] = this.binId.orEmpty()
        data["numberLength"] = this.numberLength.toString()
        data["numberLuhn"] = this.numberLuhn.toString()
        data["binScheme"] = this.binScheme.orEmpty()
        data["binType"] = this.binType.orEmpty()
        data["binBrand"] = this.binBrand.orEmpty()
        data["countryNumeric"] = this.countryNumeric.orEmpty()
        data["countryAlpha2"] = this.countryAlpha2.orEmpty()
        data["countryName"] = this.countryName.orEmpty()
        data["countryEmoji"] = this.countryEmoji.orEmpty()
        data["countryCurrency"] = this.countryCurrency.orEmpty()
        data["countryLatitude"] = this.countryLatitude.toString()
        data["countryLongitude"] = this.countryLongitude.toString()
        data["bankName"] = this.bankName.orEmpty()
        data["bankUrl"] = this.bankUrl.orEmpty()
        data["bankPhone"] = this.bankPhone.orEmpty()
        data["bankCity"] = this.bankCity.orEmpty()
        return data
    }
}