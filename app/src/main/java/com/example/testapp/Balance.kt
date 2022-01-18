package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.Spinner
import java.math.RoundingMode
import java.text.DecimalFormat

class Balance : AppCompatActivity() {
    var fromCurrency = "PLN"
    var toCurrency = "USD"
    var input: EditText? = null
    var convertBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        input = findViewById(R.id.currencyAmount)
        convertBtn = findViewById(R.id.convertButton)

        spinnersSetup()

        convertBtn?.setOnClickListener{
            var result: Double = 0.00
            val amount: Double = input?.text.toString().toDouble()
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING

            if(fromCurrency == "PLN" && toCurrency == "USD") {
                result = amount * 0.2524
            } else if (fromCurrency == "PLN" && toCurrency == "EUR") {
                result = amount * 0.2211
            } else if (fromCurrency == "PLN" && toCurrency == "UAH") {
                result = amount * 7.11
            }

            Toast.makeText(applicationContext, df.format(result), Toast.LENGTH_LONG).show()
        }
    }

     private fun spinnersSetup(){
         val spinnerFrom: Spinner = findViewById(R.id.fromCurrencySpinner)
         val spinnerTo: Spinner = findViewById(R.id.toCurrencySpinner)

         val from = arrayOf("PLN")
         val arrAdapter1 = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from)
         spinnerFrom.adapter = arrAdapter1

         val to = arrayOf("USD", "EUR", "UAH")
         val arrAdapter2 = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to)
         spinnerTo.adapter = arrAdapter2

         spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
             override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                 fromCurrency = p0?.getItemAtPosition(p2).toString()
             }

             override fun onNothingSelected(p0: AdapterView<*>?) {
                 TODO("Not yet implemented")
             }
         }

         spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
             override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                     toCurrency = p0?.getItemAtPosition(p2).toString()
             }

             override fun onNothingSelected(p0: AdapterView<*>?) {
                 TODO("Not yet implemented")
             }
         }
     }
}
