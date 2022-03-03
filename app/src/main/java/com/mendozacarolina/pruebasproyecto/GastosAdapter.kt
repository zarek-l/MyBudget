package com.mendozacarolina.pruebasproyecto

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class GastosAdapter(private val context: Activity, val arregloServicios: ArrayList<Servicio>) : RecyclerView.Adapter<GastosAdapter.ViewHolder>(){


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textGastosServicio: TextView = view.findViewById(R.id.textGAnuales)
        val textMontoGasto: TextView = view.findViewById(R.id.textMontoGAnuales)
        val textViewFecha: TextView = view.findViewById(R.id.textViewFecha)
        val imageGastosServicio: ImageView = view.findViewById(R.id.imageGAnuales)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_gastos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return arregloServicios.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Glide.with(context).load(arregloServicios[position].imagenServicio).into(imageGastosServicio!!)
            holder.textGastosServicio.text = arregloServicios[position].nombreServicio
            holder.textViewFecha.text = arregloServicios[position].fechaSuscripcion
            holder.textMontoGasto.text = arregloServicios[position].montoSuscripcion.toString()+" $"
        }
    }
}
