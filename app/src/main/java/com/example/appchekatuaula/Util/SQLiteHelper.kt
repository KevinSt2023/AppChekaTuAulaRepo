package com.example.appchekatuaula.Util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION){
    companion object{
        private const val DB_NAME = "DB_AULAS.db"
        private const val DB_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Aula(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, detalle TEXT , ubicacionPabellon TEXT, ubicacionPiso TEXT, requisitos TEXT, imagen TEXT)")
        db.execSQL("CREATE TABLE IF NOT EXISTS Persona(codigo TEXT PRIMARY KEY, nombre TEXT, idAula INTEGER, FOREIGN KEY(idAula) REFERENCES Aula(id))")


        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LCOM 10','Laboratorio de Computo 10','Pabellon A','Piso 5','Ninguno','lcom10')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LCOM 11','Laboratorio de Computo 11','Pabellon A','Piso 5','Ninguno','lcom11')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LCOM 12','Laboratorio de Computo 12','Pabellon B','Piso 4','Ninguno','lcom12')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LCOM 13','Laboratorio de Computo 13','Pabellon B','Piso 6','Ninguno','lcom13')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LAB 8','Laboratorio de Ciencias','Pabellon A','Piso 2','Bata, Lentes y EPP','lab8')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LAB 9','Laboratorio de Ciencias','Pabellon B','Piso 3','Bata','lab9')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LAB 10','Laboratorio de Ciencias','Pabellon A','Piso 6','Bata y Lentes','lab10')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos, imagen) VALUES ('LAB 11','Laboratorio de Ciencias','Pabellon B','Piso 1','EPP','lab11')")

        db.execSQL("INSERT INTO Persona (codigo,nombre, idAula) VALUES ('N00273302', 'Kevin De La Cruz Escate',  1)")
        db.execSQL("INSERT INTO Persona (codigo,nombre, idAula) VALUES ('N00199884', 'Milagros Desposorio Camargo',  2)")
        db.execSQL("INSERT INTO Persona (codigo,nombre, idAula) VALUES ('N00123365', 'Carlos Diaz Salmuera',  3)")



    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Aula")
    }

    fun buscarAulaPorCodigo(codigo: String): String? {
        val db = this.readableDatabase
        val query = """
        SELECT Aula.nombre
        FROM Persona
        INNER JOIN Aula ON Persona.idAula = Aula.id
        WHERE Persona.codigo = ?
    """
        val cursor = db.rawQuery(query, arrayOf(codigo))
        var aula: String? = null
        if (cursor.moveToFirst()) {
            aula = cursor.getString(0)  // Nombre del Aula
        }
        cursor.close()
        return aula
    }


}