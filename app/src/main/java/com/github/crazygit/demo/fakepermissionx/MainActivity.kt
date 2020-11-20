package com.github.crazygit.demo.fakepermissionx

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val makeCallBtn = findViewById<Button>(R.id.makeCallBtn)
        makeCallBtn.setOnClickListener {
            PermissionX.request(
                this, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS
            ) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "Your denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL).apply {
                data = Uri.parse("tel:10086")
            }
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}