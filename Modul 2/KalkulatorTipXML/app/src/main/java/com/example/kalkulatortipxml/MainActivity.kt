package com.example.kalkulatortipxml

import com.example.kalkulatortipxml.databinding.ActivityMainBinding
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tipOptions = listOf("15%", "18%", "20%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)
        binding.autoCompleteTip.setAdapter(adapter)
        binding.autoCompleteTip.setText("15%", false)

        binding.autoCompleteTip.setOnClickListener {
            binding.autoCompleteTip.showDropDown()
        }

        binding.autoCompleteTip.setOnItemClickListener { _, _, _, _ ->
            updateTip()
        }

        binding.etBill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = updateTip()
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.switchRound.setOnCheckedChangeListener { _, _ -> updateTip() }

        updateTip()
    }

    private fun updateTip() {
        val billInput = binding.etBill.text.toString()

        val amount = convertTextToNumber(billInput)

        val tipText = binding.autoCompleteTip.text.toString()
        val tipPercent = tipText.removeSuffix("%").toDoubleOrNull() ?: 15.0
        val roundUp = binding.switchRound.isChecked

        val tipResult = calculateTipResult(amount, tipPercent, roundUp)

        binding.tvTotalTip.text = getString(R.string.tip_amount_result, tipResult)
    }

    private fun calculateTipResult(amount: Double, tipPercent: Double, roundUp: Boolean): String {
        var tip = tipPercent / 100 * amount
        if (roundUp) {
            tip = ceil(tip)
        }
        return NumberFormat.getCurrencyInstance(Locale.US).format(tip)
    }

    private fun convertTextToNumber(text: String): Double {
        val cleanText = text.lowercase(Locale.getDefault()).trim()
        if (cleanText.isEmpty()) return 0.0

        if (cleanText.any { it.isDigit() }) {
            return cleanText.replace(Regex("[^\\d.]"), "").toDoubleOrNull() ?: 0.0
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
}