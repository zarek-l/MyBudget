package com.mendozacarolina.pruebasproyecto

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarServicioActivity : AppCompatActivity(){

     lateinit var userId: String
     lateinit var editNombreServicio: EditText
     lateinit var editMontoSuscripcion: EditText
     lateinit var editFechaPago: EditText
     lateinit var buttonAgregarServicio: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_servicio)

        //inicializacion de variables
        userId= Firebase.auth.currentUser?.email.toString()
        editNombreServicio = findViewById(R.id.editNombreServicio)
        editMontoSuscripcion = findViewById(R.id.editMontoSuscripcion)
        editFechaPago = findViewById(R.id.editFechaPago)
        buttonAgregarServicio = findViewById(R.id.buttonAgregarServicio)

        buttonAgregarServicio.setOnClickListener {
            agregarServicio(userId)
        }

    }

    fun agregarServicio(userId: String){
        val nuevoServicio = hashMapOf<String, Any>(
            "nombreServicio" to editNombreServicio.getText().toString(),
            "imagenServicio" to "",
            "fechaSuscripcion" to editFechaPago.getText().toString(),
        )
        val db = Firebase.firestore
        val referencia = db.collection("users").document(userId!!)
            .collection("servicios")
        referencia
            .add(nuevoServicio)
            .addOnSuccessListener {
                Toast.makeText(this, R.string.mensaje_exito_servicio_agregado, Toast.LENGTH_LONG).show()
            }.addOnFailureListener {}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_cuenta -> {
                var intent = Intent(this,ConfiguracionActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_logOut -> {
                Toast.makeText(this, R.string.mensajeSalida, Toast.LENGTH_LONG).show()
                finish()
                var intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}