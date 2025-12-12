package com.example.practice1

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.practice1.ui.theme.Practice1Theme
import org.w3c.dom.Text
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practice1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ){
                   unitconverter()
                }

            }
        }
    }
}

/* @Composable
fun Rememberme_add(){
    val adder = remember {
        mutableStateOf(0)
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "added by one : ${adder.value}")
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = { adder.value += 1 }) {
            Text(text = "add one each time ")
        }
    }
}*/

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun unitconverter() {
    var inputValue by remember{mutableStateOf("")}
    var outputValue by remember{mutableStateOf("result")}
    var inputUnit by remember { mutableStateOf("select")}
    var outputUnit by remember { mutableStateOf("select")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val conversionFactor =  remember { mutableStateOf(1.00)}
    val oconversionFactor =  remember { mutableStateOf(1.00)}

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = inputValueDouble * (conversionFactor.value / oconversionFactor.value)
        outputValue = result.toString()
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Unit Converter",fontFamily = FontFamily.SansSerif, fontSize = 7.em ,)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
            inputValue = it
        }, label = {Text("Enter Value")})
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Box {
                Button(onClick ={
                    iExpanded = true;
                }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)

                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {iExpanded = false; inputUnit="centimeter";conversionFactor.value = 0.01;convertUnits()})
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {iExpanded = false; inputUnit="meter";conversionFactor.value = 1.0;convertUnits()})
                    DropdownMenuItem(text = { Text(text = "kilometer") }, onClick = {iExpanded = false; inputUnit="kilometer";conversionFactor.value = 1000.0;convertUnits()})
                }

            }
            Spacer(modifier = Modifier.padding(16.dp))
            Box {
                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "centimeter") }, onClick = {oExpanded = false; outputUnit="centimeter";oconversionFactor.value = 0.01;convertUnits()})
                    DropdownMenuItem(text = { Text(text = "meter") }, onClick = {oExpanded = false; outputUnit="meter";oconversionFactor.value = 1.0;convertUnits()})
                    DropdownMenuItem(text = { Text(text = "kilometer") }, onClick = {oExpanded = false; outputUnit="kilometer";oconversionFactor.value = 1000.0;convertUnits()})
                }

            }

        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "$outputValue $outputUnit", fontSize = 30.sp,
            style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun GreetingPreview() {
    Practice1Theme {
        Greeting("Android")
    }
}
