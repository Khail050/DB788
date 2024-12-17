package com.example.f_sqflitedb2_new

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show)

        lateinit var user: String
        lateinit var pass: String

        val id = findViewById<TextView>(R.id.id)
        val user1 = findViewById<TextView>(R.id.user)
        val pass1 = findViewById<TextView>(R.id.pass)

        val logout = findViewById<Button>(R.id.logout)
        val alldata = findViewById<Button>(R.id.alldata)

        val Id = findViewById<TextView>(R.id.Id)
        val User = findViewById<TextView>(R.id.User)
        val Pass = findViewById<TextView>(R.id.Pass)

        user = intent.getStringExtra("user").toString()
        pass = intent.getStringExtra("pass").toString()


        val db = MyUserOperations(this, null)

        var cursor = db.login(user, pass)

        if(cursor.moveToFirst()){
            id.setText(cursor.getString(0)).toString()
            user1.setText(cursor.getString(1))
            pass1.setText(cursor.getString(2))
        }

        logout.setOnClickListener {
            cursor.close()
            db.close()
            val  i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }


        alldata.setOnClickListener{
            Id.setText("Id\n---------\n")
            User.setText("User\n---------\n")
            Pass.setText("Pass\n---------\n")
            // creating an object of DBHelper class

            val db = MyUserOperations(this, null)

            // Calling method to get all names from our database
            val cursor = db.getAll()
            //Toast.makeText(this, " $cursor", Toast.LENGTH_LONG).show()
            if (cursor!!.moveToFirst()) {
                Id.append(cursor.getString(0) + "\n")
                User.append(cursor.getString(1) + "\n")
                Pass.append(cursor.getString(2) + "\n")

                // moving our cursor to next position
                while (cursor.moveToNext()) {
                    Id.append(cursor.getString(0) + "\n")
                    User.append(cursor.getString(cursor.getColumnIndexOrThrow(MyUserOperations.NAME_COl)) + "\n")
                    Pass.append(cursor.getString(cursor.getColumnIndexOrThrow(MyUserOperations.AGE_COL)) + "\n")
                }
            }
            // clearing edit texts
            alldata.text.toString()

            // close our cursor
            cursor!!.close()
        }


        // close our cursor
        cursor!!.close()
    }
}
