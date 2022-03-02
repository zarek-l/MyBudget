package com.mendozacarolina.pruebasproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var buttonIngresar: Button
    lateinit var buttonRegistrar: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonIngresar = findViewById(R.id.buttonIngresar)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //Eventos clic

        }

}