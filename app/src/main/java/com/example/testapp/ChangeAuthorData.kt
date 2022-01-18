package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ChangeAuthorData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_author_data)
        initListeners()
    }

    private fun initListeners(){
        val changeButton = findViewById<Button>(R.id.saveDataBtn)
        changeButton.setOnClickListener(saveAuthorDataButton)
    }

    private var saveAuthorDataButton: View.OnClickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.saveDataBtn -> {
                changeData()
            }
        }
    }

    private fun changeData() {
        val intent = Intent()
        val nameInput = findViewById<TextInputEditText>(R.id.inputName)
        val surnameInput = findViewById<TextInputEditText>(R.id.inputSurname)
        val emailInput = findViewById<TextInputEditText>(R.id.inputEmail)
        val gitHubInput = findViewById<TextInputEditText>(R.id.inputGitHub)

        if(
            nameInput.text.toString().isEmpty() ||
            surnameInput.text.toString().isEmpty() ||
            emailInput.text.toString().isEmpty() ||
            gitHubInput.text.toString().isEmpty()
        ) {
            setResult(RESULT_CANCELED, intent)
        } else {
            intent.putExtra("name", nameInput.text.toString())
            intent.putExtra("surname", surnameInput.text.toString())
            intent.putExtra("email", emailInput.text.toString())
            intent.putExtra("github", gitHubInput.text.toString())
            setResult(RESULT_OK, intent);
        }
        Toast.makeText(applicationContext, R.string.data_has_changed, Toast.LENGTH_LONG).show()
        finish();
    }
}