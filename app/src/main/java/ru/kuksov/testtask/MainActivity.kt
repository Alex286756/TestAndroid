package ru.kuksov.testtask

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.kuksov.testtask.repository.BinRepository
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
) : ComponentActivity() {

    @Inject
    lateinit var binRepository: BinRepository

    private lateinit var binViewModel: BinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binViewModel = ViewModelProvider(this)[BinViewModel::class.java]

        setContent {
            StartPage()
        }

        binViewModel.responseData.observe(this) {
            if (it != null) {
                binRepository.addBin(it)
//                binViewModel.setNewId(it.binId.toString())
            } else {
                Toast.makeText(this, "There is some error!", Toast.LENGTH_SHORT).show()
            }
        }

//        binViewModel.id.observe(this) {
//
//        }
    }

    @Composable
    fun StartPage() {
        val tfValue = remember{mutableStateOf("")}
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
                value = tfValue.value,
                onValueChange = {
                    if (it.isDigitsOnly() and (it.length<=8))
                        tfValue.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("12345678") },
            )
            Button(
                onClick = {
                    if (tfValue.value.matches("[0-9]{8}".toRegex())) {
                        binViewModel.getDataFromAPI(tfValue.value)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                Text(
                    text = "Получить данные",
                    fontSize = 25.sp
                )
            }

            BinFields()

            Button(
                onClick = {
                    startActivity(
                        Intent(this@MainActivity,
                            HistoryActivity::class.java)
                    )
                },
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
    private fun BinFields() {
//        val binId = binViewModel.id.value.toString()
//        val bin = binRepository.getBinById(binId)
//
//        if (bin.binId?.length != 8)
            return
//        Row {
//            Text(
//                text = "Данные по id = ",
//                fontSize = 28.sp,
//                modifier = Modifier
//                    .padding(10.dp)
//            )
//            Text(
//                text = binId,
//                fontSize = 28.sp,
//                modifier = Modifier
//                    .padding(10.dp)
//            )
//        }
//        for (dataRecord in bin.getValues()) {
//            Row ( modifier = Modifier.fillMaxWidth() ) {
//                Text (
//                    text = dataRecord.key,
//                    fontSize = 28.sp,
//                    modifier = Modifier
//                        .border(width = 1.dp, color = Color.Blue)
//                        .padding(10.dp)
//                    )
//                Text(
//                    text = dataRecord.value,
//                    fontSize = 28.sp,
//                    modifier = Modifier
//                        .border(width = 1.dp, color = Color.Blue)
//                        .padding(10.dp)
//                )
//            }
//        }
    }

}