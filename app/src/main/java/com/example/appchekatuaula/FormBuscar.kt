package com.example.appchekatuaula

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity
import com.example.appchekatuaula.Actividad.FormDetallesAlumno
import com.example.appchekatuaula.Util.SQLiteHelper

class FormBuscar : AppCompatActivity() {
    private lateinit var btnBuscar: Button
    private lateinit var btnCroquis: Button
    private lateinit var edtCodigo: TextInputEditText
    private lateinit var txtResultado: TextView
    private lateinit var dbHelper: SQLiteHelper

    private fun validarCodigoAlumno(codigo: String): Boolean {
        val regex = Regex("^N\\d{8}\$")
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
        btnCroquis = findViewById(R.id.btnCroquis) // ← El botón CROQUIS DE AULAS

        dbHelper = SQLiteHelper(this)

        edtCodigo.setText("N")

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

        edtCodigo.setOnKeyListener { _, _, _ ->
            if (edtCodigo.text.toString().isEmpty()) {
                edtCodigo.setText("N")
                edtCodigo.setSelection(1)
            }
            false
        }

        btnBuscar.setOnClickListener {
            val codigo = edtCodigo.text.toString().trim()

            if (!validarCodigoAlumno(codigo)) {
                txtResultado.text = "Código inválido. Debe empezar con 'N' seguido de 8 números."
                return@setOnClickListener
            }

            val aulaEncontrada = dbHelper.buscarAulaPorCodigo(codigo)
            if (aulaEncontrada != null) {
                val intent = Intent(this, FormDetallesAlumno::class.java)
                intent.putExtra("codigoAlumno", codigo)
                startActivity(intent)
                edtCodigo.setText("N")
            } else {
                txtResultado.text = "No se encontró un aula para ese código."
            }
        }

        // 🔥 Aquí está la magia del botón CROQUIS DE AULAS
        btnCroquis.setOnClickListener {
            mostrarDialogCroquis()
        }
    }

    private fun mostrarDialogCroquis() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_galeria)

        val imageView = dialog.findViewById<ImageView>(R.id.imageViewDialog)
        val btnNext = dialog.findViewById<Button>(R.id.btnNext)
        val btnPrev = dialog.findViewById<Button>(R.id.btnPrev)

        val imagenes = arrayOf(
            R.drawable.pabe_a,
            R.drawable.pabe_b,
            R.drawable.pabe_d
        )

        var indiceActual = 0
        imageView.setImageResource(imagenes[indiceActual])

        btnNext.setOnClickListener {
            indiceActual = (indiceActual + 1) % imagenes.size
            imageView.setImageResource(imagenes[indiceActual])
        }

        btnPrev.setOnClickListener {
            indiceActual = if (indiceActual == 0) imagenes.size - 1 else indiceActual - 1
            imageView.setImageResource(imagenes[indiceActual])
        }

        dialog.show()
    }
}


