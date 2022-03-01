package com.mendozacarolina.pruebasproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonIngresar = findViewById(R.id.buttonIngresar)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //Eventos clic
        buttonIngresar.setOnClickListener {
            val email = editTextEmail.text.toString()
            val clave = editTextPassword.text.toString()

            //Validaciones de datos requeridos y formatos
            if(!validarDatosRequeridos())
                return@setOnClickListener
            autenticarUsuario(email,clave)
        }

        buttonRegistrar.setOnClickListener {
            val intent = Intent(this,SignActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun autenticarUsuario(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent1 = Intent(this,MainActivity::class.java)
                    intent1.putExtra(LOGIN_KEY,auth.currentUser!!.email)
                    startActivity(intent1)
                    finish()
                } else {
                    Toast.makeText(baseContext, task.exception!!.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validarDatosRequeridos():Boolean{
        val email = editTextEmail.text.toString()
        val clave = editTextPassword.text.toString()
        if (email.isEmpty()) {
            editTextEmail.setError("El email es obligatorio")
            editTextEmail.requestFocus()
            return false
        }
        if (clave.isEmpty()) {
            editTextPassword.setError("La clave es obligatoria")
            editTextPassword.requestFocus()
            return false
        }
        if (clave.length < 7) {
            editTextPassword.setError("La clave debe tener al menos 7 caracteres")
            editTextPassword.requestFocus()
            return false
        }
        return true
    }
}