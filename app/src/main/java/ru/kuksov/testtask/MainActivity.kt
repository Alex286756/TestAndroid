package ru.kuksov.testtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartPage()
        }

    }
}

@Composable
@Preview(showBackground = true)
fun StartPage() {
    val data : HashMap<String, String> = HashMap()
    Column {
        InputDataPart(data)
        BinDataPart(data)
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ){
            Text(
                text = "История запросов",
                fontSize = 25.sp
            )
        }
    }
}

@Composable
fun InputDataPart(data : HashMap<String, String>) {
    var tfValue = ""
    Column {
        Text(
            text = "Введите BIN карты:",
            fontSize = 28.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(10.dp)
        )
        TextField(
            value = tfValue,
            onValueChange = {text -> tfValue = text},
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            singleLine = true,
        )
        Button(
            onClick = {getData(tfValue, data)},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ){
            Text(
                text = "Получить данные",
                fontSize = 25.sp
            )
        }
    }
}

fun getData(bin : String, result : HashMap<String, String>) {
    result["binId"] = bin
    result["binNumber"] = "binNumber"
    result["binScheme"] = "binScheme"
    result["binType"] = "binType"
    result["binBrand"] = "binBrand"
    result["binBankCountryNumeric"] = "binBankCountryNumeric"
    result["binBankCountryAlpha2"] = "binBankCountryAlpha2"
    result["binBankCountryName"] = "binBankCountryName"
    result["binBankCountryEmoji"] = "binBankCountryEmoji"
    result["binBankCountryCurrency"] = "binBankCountryCurrency"
    result["binBankCountryLatitude"] = "binBankCountryLatitude"
    result["binBankCountryLongitude"] = "binBankCountryLongitude"
    result["binBankName"] = "binBankName"
}

@Composable
fun BinDataPart(data : HashMap<String, String>) {
    Column {
        for (dataRecord in data) {
            Row {
                Text(
                    text = dataRecord.key,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(10.dp)
                )
                Text(
                    text = dataRecord.value,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(10.dp)
                )
            }
        }
    }

}