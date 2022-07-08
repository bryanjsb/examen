package com.mobileapp_matriculauniversidad.Controllers

import com.mobileapp_matriculauniversidad.Entidades.Login


object ControllerLogIn {

    private var usuarios: ArrayList<Login> = ArrayList()

    init {
        usuarios.add(Login("1", "1", "Administrador"))
        usuarios.add(Login("2", "2", "Matriculador"))
        usuarios.add(Login("3", "3", "Estudiante"))
    }

    fun obtenerUsuario(cedula: String, contrasena: String): Login? {

        var ptr: Login? = null
        for (p: Login in usuarios) {
            if (p.cedula == cedula && p.contraseña == contrasena) {
                ptr = p
            }
        }
        return ptr
    }

    fun verificarLogin(cedula: String, contrasena: String): Boolean {
        return obtenerUsuario(cedula, contrasena) != null
    }


    fun cambiarContrasenaUsuario(cedula: String, pass: String) {
        for (p: Login in usuarios) {
            if (p.cedula == cedula) {
                p.contraseña = pass
            }
        }
    }

    fun agregarLogin(login: Login) {
        usuarios.add(login)
    }
}