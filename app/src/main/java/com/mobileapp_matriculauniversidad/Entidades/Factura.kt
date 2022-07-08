package com.mobileapp_matriculauniversidad.Entidades

import java.io.Serializable

class Factura(
    var idFactura: String = "", var fecha: String = "N/A", var tipo: String = "N/A", var moneda: String = "N/A",
    var cantidad: Int = 0, var descripcion: String = "N/A", var precioUnitario: Double = 0.0, var importe: Double = 0.0
) : Serializable {
}

