package com.mobileapp_matriculauniversidad.Entidades

import java.io.Serializable

data class Login(var cedula: String, var contraseña: String, var rol: String) : Serializable {

    override fun toString(): String {
        var s = ""
        s += "Cedula${cedula} - Rol${rol}"
        return s
    }

}