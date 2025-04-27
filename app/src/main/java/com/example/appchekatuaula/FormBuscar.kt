package com.example.appchekatuaula

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appchekatuaula.Actividad.FormDetallesAlumno

class FormBuscar : AppCompatActivity() {
    private lateinit var btnBuscar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_buscar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()
    }

    private fun asignarReferencias(){
        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        btnBuscar.setOnClickListener {
            val intent = Intent(this, FormDetallesAlumno::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Aquí puedes refrescar datos o reiniciar vistas si es necesario
    }
}