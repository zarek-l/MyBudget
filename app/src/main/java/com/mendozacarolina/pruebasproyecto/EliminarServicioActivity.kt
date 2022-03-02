package com.mendozacarolina.pruebasproyecto

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EliminarServicioActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_servicio)
        consultarServiciosUsuario()
    }

    fun consultarServiciosUsuario() {
        val db = Firebase.firestore
        db.collection("servicios")
            .get()
            .addOnSuccessListener { result ->
                Log.d(EXTRA_LOGIN, "Success getting documents")
                var servicios = ArrayList<Servicio>()
                for (document in result) {
                    val servicio = document.toObject(Servicio::class.java)
                    //val jugador = document.toObject<Jugador>()
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
}