package com.example.appchekatuaula

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appchekatuaula.Util.SQLiteHelper
import com.example.appchekatuaula.Modelo.AulasDAO


class FormDetallesAlumno : AppCompatActivity() {
    private lateinit var btnRegresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_detalles_alumno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()

        val spinner = findViewById<Spinner>(R.id.spnAulas)
        val aulasDAO = AulasDAO(this)
        val listaAulas = aulasDAO.cargarAulas()

        // Extraemos solo los nombres
        val nombresAulas = listaAulas.map { it.nombre }

        // Creamos el adapter para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresAulas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignamos el adapter al Spinner
        spinner.adapter = adapter

        // --- Aquí debajo pones la captura del item seleccionado ---
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val nombreSeleccionado = nombresAulas[position]
                Toast.makeText(this@FormDetallesAlumno, "Seleccionaste: $nombreSeleccionado", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada si no selecciona nada
            }
        }
    }

    private fun asignarReferencias(){

        btnRegresar = findViewById(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            val intent = Intent(this,FormBuscar::class.java)
            startActivity(intent)
        }
    }
}