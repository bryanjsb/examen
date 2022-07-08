package com.mobileapp_matriculauniversidad.Vista.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp_matriculauniversidad.Controllers.ControllerLogIn
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.Vista.Administrador.Cliente.EstudianteAdministrador
import com.mobileapp_matriculauniversidad.Vista.Cliente.Cliente

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LogIn()
        cambiarContraseña()
    }

    private fun cambiarContraseña() {
        val btnCambiarContrasena = findViewById<Button>(R.id.btn_log_cambiarContraseña)

        btnCambiarContrasena.setOnClickListener {
            val i = Intent(this, CambiarContrasenaLoginActivity::class.java)
            startActivity(i)
        }

    }

    private fun LogIn() {

        val btnIngresar = findViewById<Button>(R.id.btn_signin_login)

        btnIngresar.setOnClickListener {
            val user = findViewById<EditText>(R.id.et_email_login).text.toString()
            val pass = findViewById<EditText>(R.id.et_password_Login).text.toString()

            if (ControllerLogIn.verificarLogin(user, pass)) {

                val ptr = ControllerLogIn.obtenerUsuario(user, pass)
                if (ptr != null) {
                    if (ptr.rol == "Administrador") {

                        val i = Intent(this, EstudianteAdministrador::class.java)

                        i.putExtra("login", ptr)
                        startActivity(i)

                    } else if (ptr.rol == "Cliente") {
                        val i = Intent(this, Cliente::class.java)
                        i.putExtra("login", ptr)
                        startActivity(i)
                    }
                }
            }
        }
    }
}