package com.example.appchekatuaula.Util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION){
    companion object{
        private const val DB_NAME = "DB_AULAS.db"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Aula(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, detalle TEXT , ubicacionPabellon TEXT, ubicacionPiso TEXT, requisitos TEXT)")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LCOM 10','Laboratorio de Computo 10','Pabellon A','Piso 5','Ninguno')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LCOM 11','Laboratorio de Computo 11','Pabellon A','Piso 5','Ninguno')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LCOM 12','Laboratorio de Computo 12','Pabellon B','Piso 4','Ninguno')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LCOM 13','Laboratorio de Computo 13','Pabellon B','Piso 6','Ninguno')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LAB 8','Laboratorio de Ciencias','Pabellon A','Piso 2','Bata, Lentes y EPP')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LAB 9','Laboratorio de Ciencias','Pabellon B','Piso 3','Bata')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LAB 10','Laboratorio de Ciencias','Pabellon A','Piso 6','Bata y Lentes')")
        db.execSQL("INSERT INTO Aula (nombre, detalle, ubicacionPabellon, ubicacionPiso, requisitos) VALUES ('LAB 11','Laboratorio de Ciencias','Pabellon B','Piso 1','EPP')")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Aula")
    }
}