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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_buscar)

        asignarReferencias()
    }

    private fun asignarReferencias() {
        // AHORA sí asignamos a las variables GLOBALES
        edtCodigo = findViewById(R.id.edtCodigo)
        txtResultado = findViewById(R.id.txtResultado)
        btnBuscar = findViewById(R.id.btnBuscar)

        dbHelper = SQLiteHelper(this)

        btnBuscar.setOnClickListener {
            val codigo = edtCodigo.text.toString()
            if (codigo.isNotEmpty()) {
                val aulaEncontrada = dbHelper.buscarAulaPorCodigo(codigo)
                if (aulaEncontrada != null) {
                    val intent = Intent(this, FormDetallesAlumno::class.java)
                    intent.putExtra("codigoAlumno", codigo) // 👈 enviamos el código
                    startActivity(intent)
                } else {
                    txtResultado.text = "No se encontró un aula para ese código."
                }
            } else {
                txtResultado.text = "Por favor ingresa un código."
            }
        }


    }

    private fun buscarAula() {
        val codigoIngresado = edtCodigo.text.toString()

        if (codigoIngresado.isEmpty()) {
            txtResultado.text = "Por favor ingresa un código."
            return
        }

        val db: SQLiteDatabase = dbHelper.readableDatabase
        val query = """
        SELECT Aula.nombre
        FROM Persona
        INNER JOIN Aula ON Persona.idAula = Aula.id
        WHERE Persona.codigo = ?
    """
        val cursor = db.rawQuery(query, arrayOf(codigoIngresado))

        if (cursor.moveToFirst()) {
            val nombreAula = cursor.getString(0)
            // Aquí cambiamos: en lugar de mostrar en txtResultado, vamos a la otra pantalla
            val intent = Intent(this, FormDetallesAlumno::class.java)
            // Puedes enviar datos si quieres, por ejemplo:
            // intent.putExtra("nombreAula", nombreAula)
            startActivity(intent)
        } else {
            txtResultado.text = "No se encontró un aula para ese código."
        }
        cursor.close()
        db.close()
    }

}

