package com.mobileapp_matriculauniversidad.Controllers

import com.mobileapp_matriculauniversidad.Entidades.Cliente


class ControllerCliente private constructor() {

    private var clientes: ArrayList<Cliente> = ArrayList()

    init {
        clientes.add(
            Cliente(
                "702610004",
                "Yendri",
                "Cartago",
                "sileni2798@gmail.com", "85862025",
                "85862025"
            )
        )
        clientes.add(
            Cliente(
                "501520362", "Donald", "San Jose",
                "donald@gmail.com", "20204562", "20204562"
            )
        )
        clientes.add(
            Cliente(
                "302540256",
                "Patricia",
                "Heredia", "patri0212@hotmail.com",
                "85489652",
                "05-02-1995",
            )
        )
    }

    private object HOLDER {
        val INSTANCE = ControllerCliente()
    }

    companion object {
        val instance: ControllerCliente by lazy {
            HOLDER.INSTANCE
        }
    }

    fun agregar(cliente: Cliente) {
        clientes.add(cliente)
    }

    fun listar(): ArrayList<Cliente> {
        return this.clientes
    }

    fun eliminar(position: Int) {
        clientes.removeAt(position)
    }

    fun actualizar(cliente: Cliente, position: Int) {
        var aux = clientes[position]

        aux.id = cliente.id
        aux.nombre = cliente.nombre
        aux.direccion = cliente.direccion
        aux.correoElectronico = cliente.correoElectronico
        aux.telefono = cliente.telefono
        aux.celular = cliente.celular
    }
}