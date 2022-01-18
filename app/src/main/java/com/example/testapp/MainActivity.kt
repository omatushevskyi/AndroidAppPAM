package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    private fun initListeners(){
        val incomeButton = findViewById<Button>(R.id.incomeBtn)
        val balanceButton = findViewById<Button>(R.id.balanceBtn)
        val personalDataButton = findViewById<Button>(R.id.shareDataBtn)
        val settingsButton = findViewById<Button>(R.id.settingsBtn)

        incomeButton.setOnClickListener(buttonIncomeListener)
        balanceButton.setOnClickListener(buttonBalanceListener)
        personalDataButton.setOnClickListener(buttonPersonalDataListener)
        settingsButton.setOnClickListener(buttonSettingsListener)
    }

    private val buttonIncomeListener = View.OnClickListener { callIncome() }
    private val buttonBalanceListener = View.OnClickListener { callBalance() }
    private val buttonPersonalDataListener = View.OnClickListener { callPersonalData() }
    private val buttonSettingsListener = View.OnClickListener { callSettings() }

    private fun callIncome() {
        val incomeIntent = Intent(this, Income::class.java)
        startActivity(incomeIntent)
    }

    private fun callBalance() {
        val balanceIntent = Intent(this, Balance::class.java)
        startActivity(balanceIntent)
    }


    private fun callPersonalData() {
        val dataIntent = Intent(this, SharePersonalData::class.java)
        startActivity(dataIntent)
    }

    private fun callSettings() {
        val settingIntent = Intent(this, AuthorPersonalData::class.java)
        startActivity(settingIntent)
    }
}