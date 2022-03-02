package com.mendozacarolina.pruebasproyecto

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignActivity : AppCompatActivity(){

    lateinit var editTextUsuario: EditText
    lateinit var editTextName: EditText
    lateinit var editTextEmailCreate: EditText
    lateinit var editTextPassCreate: EditText
    lateinit var editTextPassCreate2: EditText
    lateinit var textViewIniciar: TextView
    lateinit var buttonSign: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        editTextUsuario = findViewById(R.id.editTextUsuario)
        editTextName = findViewById(R.id.editTextName)
        editTextEmailCreate = findViewById(R.id.editTextEmailCreate)
        editTextPassCreate = findViewById(R.id.editTextPassCreate)
        editTextPassCreate2 = findViewById(R.id.editTextPassCreate2)
        textViewIniciar = findViewById(R.id.textViewIniciar)
        buttonSign = findViewById(R.id.buttonSign)

        // Initialize Firebase Auth
        auth = Firebase.auth

    }

}