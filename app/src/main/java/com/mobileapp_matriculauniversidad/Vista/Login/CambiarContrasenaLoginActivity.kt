package com.mobileapp_matriculauniversidad.Vista.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp_matriculauniversidad.Controllers.ControllerLogIn
import com.mobileapp_matriculauniversidad.R

class CambiarContrasenaLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_cambiar_contrasena)
        cancelarCambioContra()
        cambiarContraseña()
    }

    private fun cancelarCambioContra() {
        val btn_cancel = findViewById<Button>(R.id.btn_Cancelar_CambiarContra)

        btn_cancel.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

    }

    private fun cambiarContraseña() {

        val btncambiarContra = findViewById<Button>(R.id.btn_cambiarContra)

        val listaLogin = ControllerLogIn

        btncambiarContra.setOnClickListener {
            val cedulaCambiarContra = findViewById<EditText>(R.id.ed_cedulaCambiarContra).text.toString()
            val passwordActual = findViewById<EditText>(R.id.ed_contraActualCambiarContra).text.toString()
            val passwordNueva = findViewById<EditText>(R.id.ed_contraNueva_CambiarContra).text.toString()

            if (cedulaCambiarContra.isNotEmpty() || passwordActual.isNotEmpty() || passwordNueva.isNotEmpty()) {

                val ptr = listaLogin.obtenerUsuario(cedulaCambiarContra, passwordActual)
                println("ptr: $ptr")
                if (ptr != null) {
                    if (ptr.cedula == cedulaCambiarContra && ptr.contraseña == passwordActual) {

                        listaLogin.cambiarContrasenaUsuario(cedulaCambiarContra, passwordNueva)

                        val i = Intent(this, LoginActivity::class.java)
                        startActivity(i)

                    }
                }
            }
        }
    }
}