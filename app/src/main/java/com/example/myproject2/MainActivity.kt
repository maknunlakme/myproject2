package com.example.myproject2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openMap : Button = findViewById(R.id.map_button)
        openMap.setOnClickListener {
            val intent = Intent(this, MapsActivity:: class.java)
            startActivity(intent)
        }
    }
}
