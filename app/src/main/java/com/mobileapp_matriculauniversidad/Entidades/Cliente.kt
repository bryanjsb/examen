package com.mobileapp_matriculauniversidad.Entidades

import java.io.Serializable

data class Cliente(var id:String="", var nombre:String="", var direccion:String="", var correoElectronico:String="",
                   var telefono:String="", var celular:String=""): Serializable {

    var facturas: ArrayList<Factura> = ArrayList()

    fun eliminar(curso: Factura) {
        facturas.remove(curso)
    }

    fun eliminar(pos: Int) {
        facturas.removeAt(pos)
    }
}