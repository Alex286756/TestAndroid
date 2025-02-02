package ru.kuksov.testtask

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import dagger.hilt.android.AndroidEntryPoint
import ru.kuksov.testtask.repository.BinRepository
import androidx.compose.ui.platform.LocalContext
import ru.kuksov.testtask.entity.Bin
import javax.inject.Inject
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import ru.kuksov.testtask.tools.BinViewModelFactory

@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {

    @Inject
    lateinit var binRepository : BinRepository

    private lateinit var binViewModel: BinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current

            owner?.let {
                val viewModel: BinViewModel = viewModel(
                    viewModelStoreOwner = it,
                    key = "BinViewModel",
                    factory = BinViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                Main(viewModel)
//                BinFields(viewModel)
            }
        }
//        binViewModel = ViewModelProvider(this)[BinViewModel::class.java]
//
//        binViewModel.binList.observe(this) {
//            if (it != null) {
//                binViewModel.binList.value?.size
//            } else {
//                Toast.makeText(this, "There is some error!", Toast.LENGTH_SHORT).show()
//            }
//        }
//        binViewModel.getBinList()
//        setContent {
//            Column (modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .horizontalScroll(rememberScrollState())
//            ) {
//                ListPage()
//                BinData()
//                Button(
//                    onClick = {
//                        startActivity(
//                            Intent(
//                                this@HistoryActivity,
//                                MainActivity::class.java
//                            )
//                        )
//                    }
//                ) {
//                    Text(
//                        text = "На главную",
//                        fontSize = 25.sp
//                    )
//                }
//            }
//        }

//        binViewModel.getBinList()
    }

    @Composable
    fun ListPage() {
        if (binViewModel.binList.value == null)
            return

        Column (modifier = Modifier
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
        ){
            for (bin in binViewModel.binList.value!!) {
                if (bin.binId == null) {
                    Button(
                        onClick = { /*binViewModel.id.value = ""*/ },
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
                        onClick = {/*binViewModel.id.value = bin.binId!!*/},
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
//        BinFields(viewModel.id.value.toString())
    }


}

@Composable
private fun BinFields(vm: BinViewModel = viewModel()) {
    val binId = vm.currentBinId

    if (binId == "")
        return
//        if (bin.binId?.length != 8)
//            return

    BinTitleRow()
    Row {
        Text(
            text = "Данные по id = ",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(10.dp)
        )
        Text(
            text = binId,
            fontSize = 28.sp,
            modifier = Modifier
                .padding(10.dp)
        )
    }
    val binById = vm.repository.getBinById(binId)
    for (dataRecord in binById.getValues()) {
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

@Composable
fun Main(vm: BinViewModel = viewModel()) {
    val userList by vm.binList.observeAsState(listOf())
    Column {
        OutlinedTextField(
            vm.currentBinId,
            modifier= Modifier.padding(8.dp),
            label = { Text("Name") },
            onValueChange = {}
        )
        OutlinedTextField(
            vm.binList.value?.size.toString(),
            modifier= Modifier.padding(8.dp),
            label = { Text("Age") },
            onValueChange = {},
        )
        Button({  }, Modifier.padding(8.dp)) {Text("Add", fontSize = 22.sp)}
        BinList(bins = userList, look = vm::changeBinId )
        Column (modifier = Modifier
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
        ){
            BinFields(vm)
        }
    }
}

@Composable
fun BinList(bins : List<Bin>, look : (String) -> Unit) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item{ BinTitleRow()}
        items(bins) {bin -> BinRow(bin, look)  }
    }
}

@Composable
fun BinRow(bin : Bin, look : (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
        Text(bin.binId.toString(), Modifier.weight(0.1f), fontSize = 22.sp)
        Text(bin.binType.toString(), Modifier.weight(0.2f), fontSize = 22.sp)
        Text(bin.binScheme.toString(), Modifier.weight(0.2f), fontSize = 22.sp)
        Text("Look",
            Modifier
                .weight(0.2f)
                .clickable { look(bin.binId.toString()) },
            color=Color(0xFF6650a4), fontSize = 22.sp)
    }
}

@Composable
fun BinTitleRow() {
    Row(
        Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)) {
        Text("Id", color = Color.White,modifier = Modifier.weight(0.1f), fontSize = 22.sp)
        Text("Name", color = Color.White,modifier = Modifier.weight(0.2f), fontSize = 22.sp)
        Text("Age", color = Color.White, modifier = Modifier.weight(0.2f), fontSize = 22.sp)
        Spacer(Modifier.weight(0.2f))
    }
}