package com.mendozacarolina.pruebasproyecto

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ConfiguracionAdapter(private val context: Activity, val arregloUsuarios: ArrayList<Usuario>) : RecyclerView.Adapter<ConfiguracionAdapter.ViewHolder>(){

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textViewCampo: TextView = view.findViewById(R.id.textViewCampo)
        val textViewDato: TextView = view.findViewById(R.id.textViewDato)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_conf, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return arregloUsuarios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            //holder.textViewCampo.text = arregloUsuarios[position].nombreServicio
            //holder.textViewDato.text = arregloUsuarios[position].fechaSuscripcion
        }
    }

}
