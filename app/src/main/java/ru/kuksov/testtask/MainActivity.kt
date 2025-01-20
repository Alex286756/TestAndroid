package ru.kuksov.testtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
fun StartPage(viewModel: BinViewModel = viewModel()) {
    var tfValue = ""
    Column (modifier = Modifier
        .verticalScroll(rememberScrollState())
        .horizontalScroll(rememberScrollState())
    ){
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
            onClick = { viewModel.setNewId("tfValue")},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ){
            Text(
                text = "Получить данные",
                fontSize = 25.sp
            )
        }

        BinFields(viewModel.id)

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
private fun BinFields(id : String, viewModel: BinViewModel = viewModel()) {
    if (viewModel.data.size < 1)
        return
    Row {
        Text(
            text = "Данные по id = ",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(10.dp)
        )
        Text(
            text = id,
            fontSize = 28.sp,
            modifier = Modifier
                .padding(10.dp)
        )
    }
    for (dataRecord in viewModel.data) {
        Row ( modifier = Modifier.fillMaxWidth() ) {
            Text (
                text = dataRecord.key,
                fontSize = 28.sp,
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Blue)
                    .padding(10.dp)
                )
            Text(
                text = dataRecord.value,
                fontSize = 28.sp,
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Blue)
                    .padding(10.dp)
            )
        }
    }
}
