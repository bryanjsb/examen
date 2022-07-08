package com.mobileapp_matriculauniversidad.Vista.Administrador.Cliente


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp_matriculauniversidad.Controllers.ControllerCliente
import com.mobileapp_matriculauniversidad.Entidades.Cliente
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.SplashActivity
import com.mobileapp_matriculauniversidad.Vista.Administrador.Factura.ProfesorAdministrador

class InsertarEstudiante : AppCompatActivity() {

    var controllerCliente: ControllerCliente = ControllerCliente.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador_estudiante_insertar)
        setSupportActionBar(findViewById(R.id.toolbar_estudiante_insertar_administrador))
        cancelar()
        insertar()
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

    private fun insertar() {
        val ced = findViewById<EditText>(R.id.tv_cedula_estudiante_admin)
        val nombre = findViewById<EditText>(R.id.tv_nombre_estudiante_admin)
        val tel = findViewById<EditText>(R.id.tv_telefono_estudiante_admin)
        val email = findViewById<EditText>(R.id.tv_email_estudiante_admin)
        val date = findViewById<EditText>(R.id.tv_direccion_estudiante_admin)
        val carr = findViewById<EditText>(R.id.tv_celular_estudiante_admin)

        val btn_register = findViewById<Button>(R.id.btnInsertaEstudianteAdmin)


        btn_register.setOnClickListener {
            if (!ced.text.isEmpty() || !nombre.text.isEmpty() || !tel.text.isEmpty()
                || !email.text.isEmpty() || !date.text.isEmpty() || !carr.text.isEmpty()
            ) {
                val p = Cliente(
                    ced.text.toString(),
                    nombre.text.toString(),
                    date.text.toString(),
                    email.text.toString(),
                    tel.text.toString(),
                    carr.text.toString()
                )

                controllerCliente.agregar(p)
                val i = Intent(this, EstudianteAdministrador::class.java)
                startActivity(i)
            }
        }
    }

    private fun cancelar() {
        val btn_cancel = findViewById<Button>(R.id.btnCancelarEstudianteAdmin)

        btn_cancel.setOnClickListener {
            val i = Intent(this, EstudianteAdministrador::class.java)
            startActivity(i)
        }
    }
}