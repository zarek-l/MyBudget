package com.mendozacarolina.pruebasproyecto

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        buttonSign.setOnClickListener {
            val email = editTextEmailCreate.text.toString()
            val clave = editTextPassCreate.text.toString()
            //Validaciones de datos requeridos y formatos
            if(!ValidarDatosRequeridos())
                return@setOnClickListener
            CrearNuevoUsuario(email,clave)
        }
    }

    fun CrearNuevoUsuario(email:String, password:String){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Nuevo usuario agregado",  Toast.LENGTH_SHORT).show()
                        var intent2 = Intent(this,LoginActivity::class.java)
                        intent2.putExtra(LOGIN_KEY,auth.currentUser!!.email)
                        startActivity(intent2)
                        finish()
                    } else {
                        Toast.makeText(baseContext, task.exception!!.message,
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

    private fun ValidarDatosRequeridos():Boolean{
        val email = editTextEmailCreate.text.toString()
        val clave = editTextPassCreate.text.toString()
        val clave2 = editTextPassCreate2.text.toString()
        if (email.isEmpty()) {
            editTextEmailCreate.setError("El email es obligatorio")
            editTextEmailCreate.requestFocus()
            return false
        }
        if (clave.isEmpty()) {
            editTextPassCreate.setError("La clave es obligatoria")
            editTextPassCreate.requestFocus()
            return false
        }
        if (clave.length < 7) {
            editTextPassCreate.setError("La clave debe tener al menos 7 caracteres")
            editTextPassCreate.requestFocus()
            return false
        }
        if (!clave.equals(clave2)) {
            editTextPassCreate2.setError("La clave no es igual en el campo de verificación")
            editTextPassCreate2.requestFocus()
            return false
        }
        return true
    }
}