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
        val cedula = findViewById<EditText>(R.id.tv_id_factura)
        val nombre = findViewById<EditText>(R.id.tv_fecha_factura)
        val telefono = findViewById<EditText>(R.id.tv_tipo_factura)
        val email = findViewById<EditText>(R.id.tv_moneda_factura)

        val cantidad = findViewById<EditText>(R.id.txt_cantidad_factura)
        val descripcion= findViewById<EditText>(R.id.txt_descripcion_factura)
        val precioUnitario= findViewById<EditText>(R.id.txt_precioUnitario_factura)
        val importe= findViewById<EditText>(R.id.txt_importe_factura)
        val btn_register = findViewById<Button>(R.id.btnInsertarProfesor)



        btn_register.setOnClickListener {
            if (cedula.text.isNotEmpty() || nombre.text.isNotEmpty() || telefono.text.isNotEmpty()
                || email.text.isNotEmpty() || cantidad.text.isNotEmpty()
            ) {

                val p = Factura(
                    cedula.text.toString(), nombre.text.toString(),   telefono.text.toString(),email.text.toString(),cantidad.text.toString().toInt(),
                    descripcion.text.toString(),precioUnitario.text.toString().toDouble(),importe.text.toString().toDouble()
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