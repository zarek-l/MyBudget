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


class ConfiguracionAdapter(private val context: Activity, val arregloUsuarios: ArrayList<Usuario>) : RecyclerView.Adapter<ConfiguracionAdapter.ViewHolder>(){

    lateinit var arreglo : String

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textViewCampo: TextView = view.findViewById(R.id.textViewCampo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_conf, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return arregloUsuarios.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            arreglo = arregloUsuarios[position].toString()
            arreglo = arreglo.replace(",", "\n\n");
            arreglo = arreglo.replace("Usuario(", " ");
            arreglo = arreglo.replace(")", "");
            arreglo = arreglo.replace("=", ": ");
            arreglo = arreglo.replace("tipoPlan", "tipo Plan");
            holder.textViewCampo.text = arreglo
        }
    }
}
