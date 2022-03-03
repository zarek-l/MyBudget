package com.mendozacarolina.pruebasproyecto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GastosActivity : AppCompatActivity(){

    lateinit var userId: String
    lateinit var textViewAño : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_mensual)

        textViewAño = findViewById(R.id.textViewAño)
        userId= Firebase.auth.currentUser?.email.toString()
        consultarGastosServicios(userId)


        textViewAño.setOnClickListener{
            var intent = Intent(this,GastosAnualesActivity::class.java)
            startActivity(intent)
        }
    }

    fun consultarGastosServicios(userId:String) {
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
                val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewGastos);
                recyclerViewRanking.layoutManager = LinearLayoutManager(this);
                recyclerViewRanking.adapter = GastosAdapter(this, servicios, userId);
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