package com.example.f_sqflitedb2_new


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)

        lateinit var user: String
        lateinit var pass: String

        val user1 = findViewById<TextView>(R.id.user)
        val pass1 = findViewById<TextView>(R.id.pass)

        val logout = findViewById<Button>(R.id.logout)
        val update = findViewById<Button>(R.id.update)
        val delete = findViewById<Button>(R.id.delete)

        user = intent.getStringExtra("user").toString()
        pass = intent.getStringExtra("pass").toString()

        val db = MyUserOperations(this, null)

        var cursor = db.login(user, pass)

        if (cursor.moveToFirst()) {
            user1.setText(cursor.getString(1).toString())
            pass1.setText(cursor.getString(2))
        }

        update.setOnClickListener {
            val _id = cursor.getString(0).toInt()
            var response =
                db.updateName(_id, user1.text.toString().trim(), pass1.text.toString().trim())
            if (response > 0) {


                var i = Intent(this, LoginActivity::class.java)
                i.putExtra("user", user)
                i.putExtra("pass", pass)
                startActivity(i)
            } else {
                Toast.makeText(this, "Error!!", Toast.LENGTH_LONG).show()
            }
        }

        delete.setOnClickListener {
            val _id = cursor.getString(0).toInt()
            val response = db.deleteName(_id)

            if (response > 0) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this, "Delete is Error!!", Toast.LENGTH_LONG).show()
            }
        }
        logout.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            i.putExtra("user1", user)
            i.putExtra("pass1", pass)
            startActivity(i)
        }

    }
}

