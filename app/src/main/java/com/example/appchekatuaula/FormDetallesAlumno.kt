package com.example.appchekatuaula.Actividad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appchekatuaula.Adapter.DetalleAulaAdapter
import com.example.appchekatuaula.Entidad.Aulas
import com.example.appchekatuaula.FormBuscar
import com.example.appchekatuaula.Modelo.AulasDAO
import com.example.appchekatuaula.R

class FormDetallesAlumno : AppCompatActivity() {

    private lateinit var btnRegresar: Button
    private lateinit var spnAulas: Spinner
    private lateinit var rcVista: RecyclerView

    private lateinit var listaAulas: ArrayList<Aulas> // Para guardar las aulas traídas de BD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_detalles_alumno)

        asignarReferencias()
        cargarSpinner()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun asignarReferencias() {
        btnRegresar = findViewById(R.id.btnRegresar)
        spnAulas = findViewById(R.id.spnAulas)
        rcVista = findViewById(R.id.rcVista)

        rcVista.layoutManager = LinearLayoutManager(this)
        btnRegresar.setOnClickListener {
            val intent = Intent(this,FormBuscar::class.java)
            startActivity(intent)
        }
    }

    private fun cargarSpinner() {
        val aulasDAO = AulasDAO(this)
        listaAulas = aulasDAO.cargarAulas()

        val nombresAulas = arrayListOf<String>()
        nombresAulas.add("Selecciona...") // Primer elemento
        nombresAulas.addAll(listaAulas.map { it.nombre ?: "Sin nombre" })

        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresAulas)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnAulas.adapter = adapterSpinner

        spnAulas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    // No mostrar nada si es "Selecciona..."
                    rcVista.adapter = null
                    return
                }

                val aulaSeleccionada = listaAulas[position - 1] // Ajustar posición
                val listaMostrar = arrayListOf(aulaSeleccionada)

                val adapterRecycler = DetalleAulaAdapter(listaMostrar)
                rcVista.adapter = adapterRecycler
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nada
            }
        }
    }
}

