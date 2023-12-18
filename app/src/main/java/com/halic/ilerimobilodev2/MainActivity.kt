package com.halic.ilerimobilodev2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.halic.ilerimobilodev2.ui.theme.IleriMobilOdev2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IleriMobilOdev2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        CalculateLengthView()
                    }
                }
            }
        }
    }
}

@Composable
fun CalculateLengthView() {
    Column {
        var inputStart by remember {
            mutableStateOf("")
        }
        var inputCount by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Asal Sayı Bulucu", fontWeight = FontWeight.Bold)
        }

        Row {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.5f),
                value = inputStart,
                onValueChange = { inputStart = it },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(text = "Başlangıç Sayısı")
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputCount,
                onValueChange = { inputCount = it },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(text = "Bulunacak Miktar")
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        val start = inputStart.toIntOrNull()
        val count = inputCount.toIntOrNull()

        if (start == null || count == null) {
            Text(
                text = "Asal sayıları listelemek için başlangıç sayısı ve bulunacak miktarı giriniz!",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            return
        }

        if (count <= 0) {
            Text(
                text = "Bulunacak miktar  0' dan büyük olmalıdır!",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            return
        }

        if (count > 1000) {
            Text(
                text = "Bulunacak miktar  1,000' den büyük olmamalıdır!",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            return
        }

        Text(
            text = "%,d ile başlayan %,d adet asal sayı listesi:".format(start, count),
            fontWeight = FontWeight.Bold
        )

        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
        ) {
            val primeNumbers = calculate(start, count)
            Text(
                text = primeNumbers.joinToString { "(%,d)".format(it) },
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = Color.Blue
            )
        }

    }
}

fun calculate(start: Int, count: Int) : MutableList<Int> {
    val result = mutableListOf<Int>()

    var num = start

    do {
        if (isPrime(num)) {
            result.add(num)
        }
        num++
    } while (result.count() < count)

    return result
}

fun isPrime(number: Int): Boolean {
    if (number <= 1) {
        return false
    }

    var i = 2
    while (i <= number/i) {
        if (number % i == 0) {
            return false
        }
        i++
    }

    return true
}