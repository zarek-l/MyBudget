package com.mendozacarolina.pruebasproyecto

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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SignActivity : AppCompatActivity(){

    //Variables para usuario
    lateinit var editTextUsuario : EditText
    lateinit var editTextNombre : EditText
    lateinit var editTextEmailUsuario : EditText
    lateinit var editTextContrasena : EditText
    lateinit var textViewIniciaSesion : TextView
    //variable para boton
    lateinit var buttonRegistrar: Button

    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        buttonRegistrar = findViewById(R.id.buttonSign)
        editTextUsuario = findViewById(R.id.editTextUsuario)
        editTextNombre = findViewById(R.id.editTextName)
        editTextEmailUsuario = findViewById(R.id.editTextEmailCreate)
        editTextContrasena = findViewById(R.id.editTextPassCreate)
        textViewIniciaSesion = findViewById(R.id.textViewIniciar)
        buttonRegistrar = findViewById(R.id.buttonSign)
        auth = FirebaseAuth.getInstance();
        auth = Firebase.auth

        //boton registrar usuario
        buttonRegistrar.setOnClickListener{
            val email = editTextEmailUsuario.text.toString()
            val password = editTextContrasena.text.toString()

            if (!ValidarDatosRequeridos())
                return@setOnClickListener
            escribirUsuario()
            CrearUsuario(email, password)
        }
        textViewIniciaSesion.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    fun escribirUsuario(): Unit{
        db.collection("users").document(editTextEmailUsuario.text.toString()).set(
            hashMapOf("usuario" to editTextUsuario.text.toString(),
                "nombre" to editTextNombre.text.toString(),
                "correo" to editTextEmailUsuario.text.toString(),
                "tipoPlan" to "Plan Gratuito")
        )
    }

    fun CrearUsuario(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(EXTRA_LOGIN, "createUserWithEmail:success")
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra(LOGIN_KEY,auth.currentUser!!.email)
                    Toast.makeText(baseContext, "Te haz registrado exitosamente!",
                        Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(EXTRA_LOGIN, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun ValidarDatosRequeridos():Boolean{
        val email = editTextEmailUsuario.text.toString()
        val clave = editTextContrasena.text.toString()
        if (email.isEmpty()) {
            editTextEmailUsuario.setError("El email es obligatorio")
            editTextEmailUsuario.requestFocus()
            return false
        }
        if (clave.isEmpty()) {
            editTextContrasena.setError("La clave es obligatoria")
            editTextContrasena.requestFocus()
            return false
        }
        if (clave.length < 3) {
            editTextContrasena.setError("La clave debe tener al menos 3 caracteres")
            editTextContrasena.requestFocus()
            return false
        }
        return true
    }

}