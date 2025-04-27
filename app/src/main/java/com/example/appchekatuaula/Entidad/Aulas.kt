package com.example.appchekatuaula.Entidad

data class Aulas(
    var id: Int = 0,
    var nombre: String? = null,
    var detalle: String? = null,
    var ubicacionPabellon: String? = null,
    var ubicacionPiso: String? = null,
    var requisitos: String? = null,
    var imagen: String? = null
)
