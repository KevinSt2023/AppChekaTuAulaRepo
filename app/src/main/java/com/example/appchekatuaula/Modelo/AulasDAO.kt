package com.example.appchekatuaula.Modelo

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import com.example.appchekatuaula.Entidad.Aulas
import com.example.appchekatuaula.Util.SQLiteHelper
import android.util.Log

class AulasDAO(context: Context) {
    private var sqLiteHelper:SQLiteHelper = SQLiteHelper(context)

    fun cargarAulas():ArrayList<Aulas>{
        val listarAulas:ArrayList<Aulas> = ArrayList()
        val query = "SELECT * FROM Aula"
        val db =sqLiteHelper.readableDatabase
        val cursor:Cursor

        try {
            cursor =db.rawQuery(query,null)
            if(cursor.count>0){
                cursor.moveToFirst()
                do {
                    val id:Int =cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val nombre:String = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    val detalle:String = cursor.getString(cursor.getColumnIndexOrThrow("detalle"))
                    val ubicacionPabellon:String = cursor.getString(cursor.getColumnIndexOrThrow("ubicacionPabellon"))
                    val ubicacionPiso:String = cursor.getString(cursor.getColumnIndexOrThrow("ubicacionPiso"))
                    val requisitos:String = cursor.getString(cursor.getColumnIndexOrThrow("requisitos"))
                    val imagen:String = cursor.getString(cursor.getColumnIndexOrThrow("imagen"))

                    val aula = Aulas()
                    aula.nombre =nombre
                    aula.detalle = detalle
                    aula.ubicacionPabellon = ubicacionPabellon
                    aula.ubicacionPiso = ubicacionPiso
                    aula.requisitos = requisitos
                    aula.imagen = imagen
                    listarAulas.add(aula)
                }while(cursor.moveToNext())
            }
        }catch (e:Exception){
            Log.e("DB_ERROR", e.localizedMessage ?: "Error al leer la base de datos")
        }finally {
            db.close()
        }
        return listarAulas
    }
}