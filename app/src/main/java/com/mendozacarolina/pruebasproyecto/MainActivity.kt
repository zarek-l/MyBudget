package com.mendozacarolina.pruebasproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //declaracion de variables
    lateinit var buttonAgregar: Button
    lateinit var buttonGastos: Button
    lateinit var buttonEliminar: Button
    lateinit var buttonCalendario: Button
    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inicializacion de variables
        setContentView(R.layout.activity_main)
        buttonEliminar = findViewById(R.id.buttonEliminar)
        buttonGastos = findViewById(R.id.buttonGastos)
        userId= Firebase.auth.currentUser?.email.toString()


        buttonEliminar.setOnClickListener{
            var intent = Intent(this,EliminarServicioActivity::class.java)
            startActivity(intent)
        }

        buttonGastos.setOnClickListener{
            var intent = Intent(this,GastosActivity::class.java)
            startActivity(intent)
        }

        consultarServicios(userId)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun consultarServicios(userId:String) {
        val db = Firebase.firestore
        db.collection("users/"+userId+"/servicios")
            .get()
            .addOnSuccessListener { result ->
                Log.d(EXTRA_LOGIN, "Success getting documents")
                var servicios = ArrayList<Servicio>()
                for (document in result) {
                    val servicio = document.toObject(Servicio::class.java)
                    servicios.add(servicio)
                }

                //Poblar en RecyclerView información usando mi adaptador
                val recyclerServicios: RecyclerView = findViewById(R.id.recyclerServicios);
                recyclerServicios.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                recyclerServicios.adapter = MainAdapter(this, servicios);
                recyclerServicios.setHasFixedSize(true);
            }

            .addOnFailureListener { exception ->
                Log.w(EXTRA_LOGIN, "Error getting documents.", exception)
                Toast.makeText(this, "Error al obtener datos de los servicios", Toast.LENGTH_LONG)
                    .show()
            }
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


