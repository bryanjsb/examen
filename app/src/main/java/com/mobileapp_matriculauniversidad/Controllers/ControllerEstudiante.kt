package com.mobileapp_matriculauniversidad.Controllers

import com.mobileapp_matriculauniversidad.Entidades.Cliente


class ControllerEstudiante private constructor() {

    private var estudiantes: ArrayList<Cliente> = ArrayList()

    init {
        estudiantes.add(
            Cliente(
                "702610004",
                "Yendri",
                "Cartago",
                "sileni2798@gmail.com",   "85862025",
                "85862025"
            )
        )
        estudiantes.add(Cliente("501520362", "Donald", "San Jose",
            "donald@gmail.com", "20204562", "20204562"))
        estudiantes.add(
            Cliente(
                "302540256",
                "Patricia",
                "Heredia","patri0212@hotmail.com",
                "85489652",
                "05-02-1995",
            )
        )
    }

    private object HOLDER {
        val INSTANCE = ControllerEstudiante()
    }

    companion object {
        val instance: ControllerEstudiante by lazy {
            HOLDER.INSTANCE
        }
    }

    fun agregar(estudiante: Cliente) {
        estudiantes.add(estudiante)
    }

    fun listar(): ArrayList<Cliente> {
        return this.estudiantes
    }

    fun eliminar(position: Int) {
        estudiantes.removeAt(position)
    }

    fun actualizar(estudiante: Cliente, position: Int) {
        var aux = estudiantes[position]

        aux.id = estudiante.id
        aux.nombre = estudiante.nombre
        aux.direccion = estudiante.direccion
        aux.correoElectronico = estudiante.correoElectronico
        aux.telefono = estudiante.telefono
        aux.celular = estudiante.celular
    }
}