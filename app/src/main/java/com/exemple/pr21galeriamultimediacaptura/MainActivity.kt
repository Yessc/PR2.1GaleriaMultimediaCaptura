package com.exemple.pr21galeriamultimediacaptura

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView


    val getContent = registerForActivityResult(GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        uri?.let {
            imageView.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectButton = findViewById<Button>(R.id.btnGaleria)
        imageView = findViewById(R.id.imageVista)

        selectButton.setOnClickListener {
            getContent.launch("image/*")
        }
    }
}