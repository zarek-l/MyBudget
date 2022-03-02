package com.mendozacarolina.pruebasproyecto

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ServiciosAdapter(private val context: Activity, val arregloServicios: ArrayList<Servicio>) : RecyclerView.Adapter<ServiciosAdapter.ViewHolder>(){

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textViewServicio: TextView = view.findViewById(R.id.textViewServicio)
        val imageViewServicios: ImageView = view.findViewById(R.id.imageViewServicios)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_simple, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return arregloServicios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Glide.with(context).load(arregloServicios[position].imagenServicio).into(imageViewServicios!!)
            textViewServicio.text = "${arregloServicios[position].nombreServicio}"
        }
    }
}
