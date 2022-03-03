package com.mendozacarolina.pruebasproyecto

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainAdapter(private val context: Activity, val arregloServicios: ArrayList<Servicio>) : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val imageViewServicios: ImageView = view.findViewById(R.id.imageServicioGasto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_servicios, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return arregloServicios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Glide.with(context).load(arregloServicios[position].imagenServicio).into(imageViewServicios)
        }
    }

}
