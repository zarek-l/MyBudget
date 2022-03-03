package com.mendozacarolina.pruebasproyecto

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EliminarServicioActivity : AppCompatActivity(){

     lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_servicio)

        //inicializacion de variables
        //userId = intent.extras!!.getString("LOGIN_KEY")!!
        userId= "carolina.mendoza@epn.edu.ec"
        consultarServiciosUsuario(userId)
    }

    fun consultarServiciosUsuario(userId:String) {
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
                val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewServicios);
                recyclerViewRanking.layoutManager = LinearLayoutManager(this);
                recyclerViewRanking.adapter = ServiciosAdapter(this, servicios);
                recyclerViewRanking.setHasFixedSize(true);
            }

            .addOnFailureListener { exception ->
                Log.w(EXTRA_LOGIN, "Error getting documents.", exception)
                Toast.makeText(this, "Error al obtener datos de los servicios", Toast.LENGTH_LONG)
                    .show()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_cuenta -> {
                Toast.makeText(this,"cuenta", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_logOut -> {
                Toast.makeText(this, R.string.mensajeSalida, Toast.LENGTH_LONG).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}