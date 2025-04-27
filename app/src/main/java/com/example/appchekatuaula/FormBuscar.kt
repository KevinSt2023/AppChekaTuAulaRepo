package com.example.appchekatuaula

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity
import com.example.appchekatuaula.Actividad.FormDetallesAlumno
import com.example.appchekatuaula.Util.SQLiteHelper

class FormBuscar : AppCompatActivity() {
    private lateinit var btnBuscar: Button
    private lateinit var edtCodigo: TextInputEditText
    private lateinit var txtResultado: TextView
    private lateinit var dbHelper: SQLiteHelper

    // FUNCIÓN de validación
    private fun validarCodigoAlumno(codigo: String): Boolean {
        val regex = Regex("^N\\d{8}\$")  // ^ inicia con N, \d{8} = 8 números, $ termina
        return regex.matches(codigo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_buscar)

        asignarReferencias()
    }

    private fun asignarReferencias() {
        edtCodigo = findViewById(R.id.edtCodigo)
        txtResultado = findViewById(R.id.txtResultado)
        btnBuscar = findViewById(R.id.btnBuscar)

        dbHelper = SQLiteHelper(this)

        // Poner la letra N al inicio
        edtCodigo.setText("N")

        // Escuchar los cambios en el texto
        edtCodigo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && edtCodigo.text?.isEmpty() == true) {
                edtCodigo.setText("N")
                edtCodigo.setSelection(edtCodigo.text?.length ?: 1)
            }
        }

        edtCodigo.setOnClickListener {
            if (edtCodigo.text?.isEmpty() == true) {
                edtCodigo.setText("N")
                edtCodigo.setSelection(edtCodigo.text?.length ?: 1)
            }
        }

        edtCodigo.setOnKeyListener { v, keyCode, event ->
            if (edtCodigo.text.toString().isEmpty()) {
                edtCodigo.setText("N")
                edtCodigo.setSelection(1)
            }
            false
        }

        btnBuscar.setOnClickListener {
            val codigo = edtCodigo.text.toString().trim()

            // Validamos el formato correcto
            if (!validarCodigoAlumno(codigo)) {
                txtResultado.text = "Código inválido. Debe empezar con 'N' seguido de 8 números."
                return@setOnClickListener
            }

            // Buscamos en base de datos si existe ese código
            val aulaEncontrada = dbHelper.buscarAulaPorCodigo(codigo)
            if (aulaEncontrada != null) {
                val intent = Intent(this, FormDetallesAlumno::class.java)
                intent.putExtra("codigoAlumno", codigo)
                startActivity(intent)
                edtCodigo.setText("N") // ← Opcional: limpia y deja la N
            } else {
                txtResultado.text = "No se encontró un aula para ese código."
            }
        }
    }

}


