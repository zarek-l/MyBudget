package com.mendozacarolina.pruebasproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //declaracion de variables
    lateinit var buttonAgregar: Button
    lateinit var buttonGastos: Button
    lateinit var buttonEliminar: Button
    lateinit var buttonCalendario: Button
    lateinit var gridViewServicios : GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonEliminar = findViewById(R.id.buttonLogin)

        buttonEliminar.setOnClickListener{
            var intent = Intent(this,EliminarServicioActivity::class.java)
            startActivity(intent)
        }
    }
}
