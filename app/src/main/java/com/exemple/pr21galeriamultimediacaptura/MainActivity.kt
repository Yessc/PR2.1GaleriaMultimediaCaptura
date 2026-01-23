package com.exemple.pr21galeriamultimediacaptura

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var photoUri: Uri

    val getContent = registerForActivityResult(GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        uri?.let {
            imageView.setImageURI(it)
        }
    }
    //thumbail
    private val takePicturePreview =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                imageView.setImageBitmap(it)
            }
        }
    //metdod para full size
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                imageView.setImageURI(photoUri)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectButton = findViewById<Button>(R.id.btnGaleria)
        val btnThumbnail = findViewById<Button>(R.id.btnThumbnail)
        val btnFull = findViewById<Button>(R.id.btnFull)
        imageView = findViewById(R.id.imageVista)

        //galeria
        selectButton.setOnClickListener {
            getContent.launch("image/*")
        }

        //tumbanail
        btnThumbnail.setOnClickListener {
            takePicturePreview.launch(null)
        }
        //full size
        btnFull.setOnClickListener {

            val file = File.createTempFile(
                "photo_",
                ".jpg",
                externalCacheDir
            )

            photoUri = FileProvider.getUriForFile(
                this,
                "com.exemple.pr21galeriamultimediacaptura.fileprovider",
                file
            )

            takePicture.launch(photoUri)
        }
    }
}