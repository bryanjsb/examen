package com.mobileapp_matriculauniversidad.Controllers

import com.mobileapp_matriculauniversidad.Entidades.Factura

class ControllerFactura private constructor() {

    private var facturas: ArrayList<Factura> = ArrayList()

    init {
        facturas.add(
            Factura(
                "1", "lunes", "crédito", "colones", 2,
                "Servicios", 100.0, 200.0
            )
        )
        facturas.add(
            Factura(
                "2", "lunes", "contado", "colones", 1,
                "Servicios", 100.0, 200.0
            )
        )
        facturas.add(
            Factura(
                "3", "lunes", "crédito", "dólares", 3,
                "Servicios", 100.0, 200.0
            )
        )
    }

    private object HOLDER {
        val INSTANCE = ControllerFactura()
    }

    companion object {
        val instance: ControllerFactura by lazy {
            HOLDER.INSTANCE
        }
    }

    fun agregar(factura: Factura) {
        facturas.add(factura)
    }

    fun listar(): ArrayList<Factura> {
        return this.facturas
    }

    fun eliminar(position: Int) {
        facturas.removeAt(position)
    }

    fun actualizar(p: Factura, position: Int) {
        var aux = facturas[position]
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