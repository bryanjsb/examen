package com.mobileapp_matriculauniversidad.Vista.Administrador.Factura


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp_matriculauniversidad.Controllers.ControllerFactura
import com.mobileapp_matriculauniversidad.Entidades.Factura
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.SplashActivity
import com.mobileapp_matriculauniversidad.Vista.Administrador.Cliente.EstudianteAdministrador

class InsertarProfesor : AppCompatActivity() {

    var controllerFactura: ControllerFactura = ControllerFactura.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador_profesor_insertar)
        setSupportActionBar(findViewById(R.id.toolbar_profesor_insertar_administrador))

        cancelar()
        insertar()
    }

    private fun insertar() {
        val cedula = findViewById<EditText>(R.id.txt_cedula)
        val nombre = findViewById<EditText>(R.id.txt_nombre)
        val telefono = findViewById<EditText>(R.id.txt_telefono)
        val email = findViewById<EditText>(R.id.txt_email)
        val btn_register = findViewById<Button>(R.id.btnInsertarProfesor)

        var clave = findViewById<EditText>(R.id.txt_contra)

        btn_register.setOnClickListener {
            if (cedula.text.isNotEmpty() || nombre.text.isNotEmpty() || telefono.text.isNotEmpty()
                || email.text.isNotEmpty() || clave.text.isNotEmpty()
            ) {

                val p = Factura(
                    cedula.text.toString(), clave.text.toString(),
                    nombre.text.toString(), telefono.text.toString()
                )

                controllerFactura.agregar(p)
                val i = Intent(this, ProfesorAdministrador::class.java)
                startActivity(i)
            }
        }
    }

    private fun cancelar() {
        val btn_cancel = findViewById<Button>(R.id.btnCancelarProfesor)
        btn_cancel.setOnClickListener {
            val i = Intent(this, ProfesorAdministrador::class.java)
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_principal_administrador, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.item_princ_admin_facturas -> {
                Toast.makeText(applicationContext, "Activity Profesores Administrador", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfesorAdministrador::class.java))
            }
            R.id.item_princ_admin_clientes -> {
                Toast.makeText(applicationContext, "Activity Estudiantes Administrador", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, EstudianteAdministrador::class.java))
            }
            R.id.item_princ_admin_salir -> {
                Toast.makeText(applicationContext, "Saliendo Administrador... ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SplashActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}