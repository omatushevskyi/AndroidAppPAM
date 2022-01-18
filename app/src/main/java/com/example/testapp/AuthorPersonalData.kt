package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class AuthorPersonalData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author_personal_data)
        initListeners()
        getPersonalData()
    }

    private fun initListeners(){
        val changeButton = findViewById<Button>(R.id.changeDataBtn)
        changeButton.setOnClickListener(changeAuthorDataButton)
    }

    private var changeAuthorDataButton: View.OnClickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.changeDataBtn -> {
                val i = Intent(this, ChangeAuthorData::class.java)
                startActivityForResult(i, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);

        var name: String = ""
        var surname: String = ""
        var email: String = ""
        var github: String = ""

        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                name = if(data!!.getStringExtra("name")!!.isNotEmpty()) {
                    data.getStringExtra("name")!!;
                } else {
                    "No name"
                }
                surname = if(data!!.getStringExtra("surname")!!.isNotEmpty()) {
                    data.getStringExtra("surname")!!;
                } else {
                    "No surname"
                }
                email = if(data!!.getStringExtra("email")!!.isNotEmpty()) {
                    data.getStringExtra("email")!!;
                } else {
                    "No email"
                }
                github = if(data!!.getStringExtra("github")!!.isNotEmpty()) {
                    data.getStringExtra("github")!!;
                } else {
                    "No github"
                }
            }
        }
        val authorName = findViewById<TextView>(R.id.nameAuthorTxt)
        val authorSurname = findViewById<TextView>(R.id.surnameAuthorTxt)
        val authorEmail = findViewById<TextView>(R.id.emailAuthorTxt)
        val authorGitHub = findViewById<TextView>(R.id.githubAuthorTxt)
        authorName.text = name
        authorSurname.text = surname
        authorEmail.text = email
        authorGitHub.text = github
    }


    private fun getPersonalData() {
        val intent = Intent()
        val nameString = findViewById<TextView>(R.id.nameAuthorTxt)
        val surnameString = findViewById<TextView>(R.id.surnameAuthorTxt)
        val emailString = findViewById<TextView>(R.id.emailAuthorTxt)
        val gitHubString = findViewById<TextView>(R.id.githubAuthorTxt)

        if(
            nameString.text.toString().isEmpty() ||
            surnameString.text.toString().isEmpty() ||
            emailString.text.toString().isEmpty() ||
            gitHubString.text.toString().isEmpty()
        ) {
            setResult(RESULT_CANCELED, intent)
        } else {
            intent.putExtra("name", nameString.text.toString())
            intent.putExtra("surname", surnameString.text.toString())
            intent.putExtra("email", emailString.text.toString())
            intent.putExtra("github", gitHubString.text.toString())
            setResult(RESULT_OK, intent);
        }
    }
}