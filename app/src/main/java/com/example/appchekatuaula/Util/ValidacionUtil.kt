package com.example.appchekatuaula.Util

object ValidacionUtil {
    fun validarCodigoAlumno(codigo: String): Boolean {
        val regex = Regex("^N\\d{8}\$")
        return regex.matches(codigo)
    }
}
