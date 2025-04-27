package com.example.appchekatuaula.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appchekatuaula.Entidad.Aulas
import com.example.appchekatuaula.R

class DetalleAulaAdapter(private val listaDetalles: ArrayList<Aulas>) : RecyclerView.Adapter<DetalleAulaAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDetalle: TextView = itemView.findViewById(R.id.txtDetalle)
        val txtUbicacionPabellon: TextView = itemView.findViewById(R.id.txtUbicacionPabellon)
        val txtUbicacionPiso: TextView = itemView.findViewById(R.id.txtUbicacionPiso)
        val txtRequisitos: TextView = itemView.findViewById(R.id.txtRequisitos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_detalle_aula, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aula = listaDetalles[position]
        holder.txtDetalle.text = "Detalle: ${aula.detalle}"
        holder.txtUbicacionPabellon.text = "Pabellón: ${aula.ubicacionPabellon}"
        holder.txtUbicacionPiso.text = "Piso: ${aula.ubicacionPiso}"
        holder.txtRequisitos.text = "Requisitos: ${aula.requisitos}"
    }

    override fun getItemCount(): Int {
        return listaDetalles.size
    }
}
