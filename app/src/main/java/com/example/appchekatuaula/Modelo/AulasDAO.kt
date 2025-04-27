package com.example.appchekatuaula.Modelo

import android.content.Context
import android.database.Cursor
import com.example.appchekatuaula.Entidad.Aulas
import com.example.appchekatuaula.Util.SQLiteHelper
import android.util.Log

class AulasDAO(context: Context) {
    private var sqLiteHelper: SQLiteHelper = SQLiteHelper(context)

    fun cargarAulas(): ArrayList<Aulas> {
        val listarAulas = ArrayList<Aulas>()
        val query = "SELECT * FROM Aula"
        val db = sqLiteHelper.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    val detalle = cursor.getString(cursor.getColumnIndexOrThrow("detalle"))
                    val ubicacionPabellon = cursor.getString(cursor.getColumnIndexOrThrow("ubicacionPabellon"))
                    val ubicacionPiso = cursor.getString(cursor.getColumnIndexOrThrow("ubicacionPiso"))
                    val requisitos = cursor.getString(cursor.getColumnIndexOrThrow("requisitos"))
                    val imagen = cursor.getString(cursor.getColumnIndexOrThrow("imagen"))

                    val aula = Aulas(
                        id = id,
                        nombre = nombre,
                        detalle = detalle,
                        ubicacionPabellon = ubicacionPabellon,
                        ubicacionPiso = ubicacionPiso,
                        requisitos = requisitos,
                        imagen = imagen
                    )
                    listarAulas.add(aula)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e("DB_ERROR", e.localizedMessage ?: "Error al leer la base de datos")
        } finally {
            cursor?.close()
            db.close()
        }
        return listarAulas
    }

    fun obtenerAulasPorAlumno(codigoAlumno: String): ArrayList<Aulas> {
        val lista = ArrayList<Aulas>()
        val db = sqLiteHelper.readableDatabase
        var cursor: Cursor? = null

        try {
            val query = """
                SELECT Aula.id, Aula.nombre, Aula.detalle, Aula.ubicacionPabellon, Aula.ubicacionPiso, Aula.requisitos, Aula.imagen
                FROM Persona
                INNER JOIN Aula ON Persona.idAula = Aula.id
                WHERE Persona.codigo = ?
            """

            cursor = db.rawQuery(query, arrayOf(codigoAlumno))

            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(0)
                    val nombre = cursor.getString(1)
                    val detalle = cursor.getString(2)
                    val ubicacionPabellon = cursor.getString(3)
                    val ubicacionPiso = cursor.getString(4)
                    val requisitos = cursor.getString(5)
                    val imagen = cursor.getString(6)

                    val aula = Aulas(
                        id = id,
                        nombre = nombre,
                        detalle = detalle,
                        ubicacionPabellon = ubicacionPabellon,
                        ubicacionPiso = ubicacionPiso,
                        requisitos = requisitos,
                        imagen = imagen
                    )
                    lista.add(aula)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e("DB_ERROR", e.localizedMessage ?: "Error al leer aulas del alumno")
        } finally {
            cursor?.close()
            db.close()
        }

        return lista
    }
}
