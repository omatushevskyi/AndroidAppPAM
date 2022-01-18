package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.WriterException

class SharePersonalData : AppCompatActivity() {
    var image: ImageView? = null
    var btnGenerate: Button? = null
    private var launcher: ActivityResultLauncher<Intent>? = null

    var name: String? = null
    var surname: String? = null
    var email: String? = null
    var github: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        image = findViewById(R.id.qrCodeImage)
        btnGenerate = findViewById(R.id.personalDataQRButton)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->

            if(result.resultCode == RESULT_OK){
                name = result.data?.getStringExtra("name")
                surname = result.data?.getStringExtra("surname")
                email = result.data?.getStringExtra("email")
                github = result.data?.getStringExtra("github")
            }
//            Log.d("MyLog", "Result from Author Data $name $surname $email $github"

            val textLine = "Name: $name\nSurname: $surname\nE-mail: $email\nGitHub: $github"
            generateQrCode(textLine)
        }

        btnGenerate?.setOnClickListener {
            launcher?.launch(Intent(this, AuthorPersonalData::class.java))
            Toast.makeText(applicationContext, R.string.data_has_generated, Toast.LENGTH_LONG).show()
        }
    }

    private fun generateQrCode(text: String) {
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 1000)
        try {
            val bMap = qrGenerator.encodeAsBitmap()
            image?.setImageBitmap(bMap)

        } catch (e: WriterException) {

        }
    }
}