package com.example.f_sqflitedb2_new


import MyUserOperations
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val user1 = findViewById<TextView>(R.id.user)
        val pass1 = findViewById<TextView>(R.id.pass)

        val login = findViewById<Button>(R.id.login)
        val update = findViewById<Button>(R.id.update)
        val create = findViewById<Button>(R.id.create)
        val home = findViewById<Button>(R.id.home)

//        user = intent.getStringExtra("user").toString()
//        pass = intent.getStringExtra("pass").toString()

        create.setOnClickListener {
            if(user1.text.toString().isEmpty() || pass1.text.toString().isEmpty()){
                Toast.makeText(this, "fields cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                val db = MyUserOperations(this,null)
                var response = db.addName(user1.text.toString().trim(), pass1.text.toString().trim())

                if(response> 0) {
//
                    Toast.makeText(this, "Add user", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this, "Not Insert!!", Toast.LENGTH_LONG).show()
                }
            }
        }

        login.setOnClickListener {
            if(user1.text.toString().isEmpty() || pass1.text.toString().isEmpty()){
                Toast.makeText(this, "fields cannot be empty", Toast.LENGTH_SHORT).show()
            }

            else{
                val db = MyUserOperations(this,null)

                var cursor = db.login(user1.text.toString().trim(),pass1.text.toString().trim())

                if (cursor.count>0){

                    var i = Intent(this, ShowActivity::class.java)
                    // i.putExtra("id1", id1)
                    i.putExtra("user",user1.text.toString())
                    i.putExtra("pass",pass1.text.toString())
                    startActivity(i)

                }else
                    Toast.makeText(this, "mobile or code is wrong", Toast.LENGTH_SHORT).show()
            }
        }
        update.setOnClickListener{
            var i = Intent(this, UpdateActivity::class.java)
            i.putExtra("user",user1.text.toString())
            i.putExtra("pass",pass1.text.toString())
            startActivity(i)
        }
        home.setOnClickListener{
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }
}