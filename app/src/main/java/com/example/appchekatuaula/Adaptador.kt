package com.example.appchekatuaula.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appchekatuaula.Entidad.Aulas
import com.example.appchekatuaula.R

class DetalleAulaAdapter(
    private val listaDetalles: ArrayList<Aulas>
) : RecyclerView.Adapter<DetalleAulaAdapter.ViewHolder>() {

    // ViewHolder: Representa cada item de la lista
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAula: ImageView = itemView.findViewById(R.id.imgAula)
        val txtDetalle: TextView = itemView.findViewById(R.id.txtDetalle)
        val txtUbicacionPabellon: TextView = itemView.findViewById(R.id.txtUbicacionPabellon)
        val txtUbicacionPiso: TextView = itemView.findViewById(R.id.txtUbicacionPiso)
        val txtRequisitos: TextView = itemView.findViewById(R.id.txtRequisitos)
    }

    // Crea la vista de cada item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detalle_aula, parent, false)
        return ViewHolder(vista)
    }

    // Asigna datos a cada item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aula = listaDetalles[position]

        holder.txtDetalle.text = "Detalle: ${aula.detalle}"
        holder.txtUbicacionPabellon.text = "Pabellón: ${aula.ubicacionPabellon}"
        holder.txtUbicacionPiso.text = "Piso: ${aula.ubicacionPiso}"
        holder.txtRequisitos.text = "Requisitos: ${aula.requisitos}"

        // Cargar la imagen del aula
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(
            aula.imagen, "drawable", context.packageName
        )

        if (resourceId != 0) {
            holder.imgAula.setImageResource(resourceId)
        } else {
            // Si no se encuentra la imagen, se usa una imagen por defecto
            holder.imgAula.setImageResource(R.drawable.imagen_no_disponible)
        }
    }

    // Devuelve el tamaño de la lista
    override fun getItemCount(): Int {
        return listaDetalles.size
    }
}

