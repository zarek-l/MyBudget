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


class AnualAdapter(private val context: Activity, val arregloServicios: ArrayList<Servicio>) : RecyclerView.Adapter<AnualAdapter.ViewHolder>(){


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textGAnuales: TextView = view.findViewById(R.id.textGAnuales)
        val textMontoGAnuales: TextView = view.findViewById(R.id.textMontoGAnuales)
        val imageGAnuales: ImageView = view.findViewById(R.id.imageGAnuales)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_gastos_anuales, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return arregloServicios.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Glide.with(context).load(arregloServicios[position].imagenServicio).into(imageGAnuales!!)
            holder.textGAnuales.text = arregloServicios[position].nombreServicio
            holder.textMontoGAnuales.text = arregloServicios[position].montoSuscripcion.toString()+" $"
        }
    }

}
