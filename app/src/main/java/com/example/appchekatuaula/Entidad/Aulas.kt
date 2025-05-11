package com.example.appchekatuaula.Entidad

data class Aulas(
    var id: Int = 0,
    var nombre: String? = null,
    var detalle: String? = null,
    var ubicacionPabellon: String? = null, // Asegúrate de que sea 'ubicacionPabellon' en vez de 'ubicacion_pabellon'
    var ubicacionPiso: String? = null, // Cambiar 'ubicacion_piso' por 'ubicacionPiso'
    var requisitos: String? = null,
    var imagen: String? = null
)
