package ru.kuksov.testtask

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.kuksov.testtask.repository.BinRepository
import javax.inject.Inject

@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {

    @Inject
    lateinit var binRepository : BinRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (modifier = Modifier
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())
            ) {
                ListPage()
                BinData()
                Button(
                    onClick = {
                        startActivity(
                            Intent(
                                this@HistoryActivity,
                                MainActivity::class.java
                            )
                        )
                    }
                ) {
                    Text(
                        text = "На главную",
                        fontSize = 25.sp
                    )
                }
            }
        }

    }

    @Composable
    fun ListPage(viewModel: BinViewModel = viewModel()) {
        if (binRepository.binList.value == null)
            return

        Column (modifier = Modifier
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
        ){
            for (bin in binRepository.binList.value!!) {
                if (bin.binId == null) {
                    Button(
                        onClick = { viewModel.id = "" },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "пусто",
                            fontSize = 25.sp
                        )
                    }
                } else {
                    Button(
                        onClick = {viewModel.id = bin.binId!!},
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ){
                        Text(
                            text = bin.binId!!,
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun BinData(viewModel: BinViewModel = viewModel()) {
        BinFields(viewModel.id)
    }

    @Composable
    private fun BinFields(id : String) {
        val bin = binRepository.getBinById(id)

        if (bin.binId?.length != 8)
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
        for (dataRecord in bin.getValues()) {
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

}