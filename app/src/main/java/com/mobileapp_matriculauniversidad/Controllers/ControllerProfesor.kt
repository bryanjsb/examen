package com.mobileapp_matriculauniversidad.Controllers

import com.mobileapp_matriculauniversidad.Entidades.Factura

class ControllerProfesor private constructor() {

    private var profesores: ArrayList<Factura> = ArrayList()

    init {
        profesores.add(Factura("1", "lunes", "crédito", "colones",2,
            "Servicios",100.0,200.0))
        profesores.add(Factura("2", "lunes", "contado", "colones", 1,
            "Servicios",100.0,200.0))
        profesores.add(Factura("3", "lunes", "crédito", "dólares", 3,
            "Servicios",100.0,200.0))
    }

    private object HOLDER {
        val INSTANCE = ControllerProfesor()
    }

    companion object {
        val instance: ControllerProfesor by lazy {
            HOLDER.INSTANCE
        }
    }

    fun agregar(persona: Factura) {
        profesores.add(persona)
    }

    fun listar(): ArrayList<Factura> {
        return this.profesores
    }

    fun eliminar(position: Int) {
        profesores.removeAt(position)
    }

    fun actualizar(p: Factura, position: Int) {
        var aux = profesores[position]
        aux.idFactura = p.idFactura
        aux.fecha = p.fecha
        aux.tipo = p.tipo
        aux.moneda = p.moneda
        aux.cantidad = p.cantidad
        aux.descripcion = p.descripcion
        aux.precioUnitario = p.precioUnitario
        aux.importe = p.importe
    }
}