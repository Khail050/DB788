package com.example.f_sqflitedb2_new


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val login = findViewById<Button>(R.id.login)

        val close = findViewById<Button>(R.id.close)



        login.setOnClickListener{
            var i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        close.setOnClickListener {
            exitProcess(-1)

        }

    }
}