package com.example.kalkulatortipcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalkulatortipcompose.ui.theme.TipCalculatorTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculatorApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculatorApp() {
    var tfAmountInput by remember { mutableStateOf("") }
    var swRoundUp by remember { mutableStateOf(false) }

    val tipOptions = listOf("15%", "18%", "20%")
    var spSelectedOptionText by remember { mutableStateOf(tipOptions[0]) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val amount = convertTextToNumber(tfAmountInput)

    val tipPercent = spSelectedOptionText.removeSuffix("%").toDoubleOrNull() ?: 15.0

    val finalTipResult = calculateTip(amount = amount, tipPercent = tipPercent, roundUp = swRoundUp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(Alignment.Start)
        )

        EditNumberField(
            label = stringResource(R.string.bill_amount),
            leadingIcon = Icons.Filled.Money,
            value = tfAmountInput,
            onValueChanged = { tfAmountInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded,
            onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        ) {
            TextField(
                readOnly = true,
                value = spSelectedOptionText,
                onValueChange = {},
                label = { Text(stringResource(R.string.how_was_the_service)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                tipOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            spSelectedOptionText = selectionOption
                            isDropdownExpanded = false
                        }
                    )
                }
            }
        }

        RoundTheTipRow(
            roundUp = swRoundUp,
            onRoundUpChanged = { swRoundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = stringResource(R.string.tip_amount, finalTipResult),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun EditNumberField(
    label: String,
    leadingIcon: ImageVector,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(label) },
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance(Locale.US).format(tip)
}

private fun convertTextToNumber(text: String): Double {
    val cleanText = text.lowercase(Locale.getDefault()).trim()

    if (cleanText.isEmpty()) return 0.0

    if (cleanText.any { it.isDigit() }) {
        val numberStr = cleanText.replace(Regex("[^\\d.]"), "")
        return numberStr.toDoubleOrNull() ?: 0.0
    }

    val dictionary = mapOf(
        "nol" to 0.0, "satu" to 1.0, "dua" to 2.0, "tiga" to 3.0, "empat" to 4.0,
        "lima" to 5.0, "enam" to 6.0, "tujuh" to 7.0, "delapan" to 8.0, "sembilan" to 9.0,
        "sepuluh" to 10.0, "sebelas" to 11.0, "seratus" to 100.0, "seribu" to 1000.0
    )

    var total = 0.0
    var blockTotal = 0.0
    var currentToken = 0.0

    val words = cleanText.split("\\s+".toRegex())

    for (word in words) {
        when {
            dictionary.containsKey(word) -> {
                currentToken = dictionary[word]!!
                if (currentToken == 100.0 || currentToken == 1000.0) {
                    blockTotal += currentToken
                    currentToken = 0.0
                }
            }
            word == "belas" -> {
                currentToken += 10.0
                blockTotal += currentToken
                currentToken = 0.0
            }
            word == "puluh" -> {
                currentToken *= 10.0
                blockTotal += currentToken
                currentToken = 0.0
            }
            word == "ratus" -> {
                currentToken *= 100.0
                blockTotal += currentToken
                currentToken = 0.0
            }
            word == "ribu" -> {
                blockTotal += currentToken
                total += if (blockTotal == 0.0) 1000.0 else blockTotal * 1000.0
                blockTotal = 0.0
                currentToken = 0.0
            }
            word == "juta" -> {
                blockTotal += currentToken
                total += if (blockTotal == 0.0) 1000000.0 else blockTotal * 1000000.0
                blockTotal = 0.0
                currentToken = 0.0
            }
        }
    }
    blockTotal += currentToken
    total += blockTotal
    return total
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TipCalculatorPreview() {
    TipCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TipCalculatorApp()
        }
    }
}