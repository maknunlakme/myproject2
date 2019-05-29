package com.example.myproject2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.telephony.PhoneNumberFormattingTextWatcher
import kotlinx.android.synthetic.main.activity_picture.*
import java.io.File
import java.io.IOException

class PictureActivity : AppCompatActivity() {

    lateinit var photoPath: String

    val REQUEST_TAKE_PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        click.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val REQUEST_TAKE_PHOTO = 1

        if(intent.resolveActivity(packageManager) != null){
            var photoFile: File? = null
            try{
                photoFile = createImageFile()
            }catch (e: IOException){}
            if(photoFile != null){

                val photoUri = FileProvider.getUriForFile(
                    this,
                    "com.example.android.fileprovider",
                    photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                startActivityForResult(intent,REQUEST_TAKE_PHOTO)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK){
            picture.rotation = 90f
            picture.setImageURI(Uri.parse(photoPath))
        }
    }

    private fun createImageFile(): File? {
        val fileName = "MyPicture"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )

        photoPath = image.absolutePath

        return image
    }
}
