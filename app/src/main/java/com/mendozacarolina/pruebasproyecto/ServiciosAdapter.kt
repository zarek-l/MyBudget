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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ServiciosAdapter(private val context: Activity, val arregloServicios: ArrayList<Servicio>) : RecyclerView.Adapter<ServiciosAdapter.ViewHolder>(){

    lateinit var userId: String

    fun <T : RecyclerView.ViewHolder> T.setOnItemClickListener(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val textViewServicio: TextView = view.findViewById(R.id.textServicioGasto)
        val imageViewServicios: ImageView = view.findViewById(R.id.imageServicioGasto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        userId= "carolina.mendoza@epn.edu.ec"
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_simple, parent, false)
        return ViewHolder(view).setOnItemClickListener { position, type ->
            Toast.makeText(parent.context, "Servicio eliminado exitosamente", Toast.LENGTH_SHORT).show()
           Firebase.firestore.collection("users").document("/$userId/servicios/"+arregloServicios[position].nombreServicio)
               .delete()
               .addOnSuccessListener {
                   Log.d(ContentValues.TAG, "DocumentSnapshot successfully deleted!") }
               .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error deleting document", e) }
        }
    }

    override fun getItemCount() : Int {
        return arregloServicios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Glide.with(context).load(arregloServicios[position].imagenServicio).into(imageViewServicios!!)
            holder.textViewServicio.text = arregloServicios[position].nombreServicio
        }
    }

}
