package com.mobileapp_matriculauniversidad.Vista.Administrador.Profesor


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp_matriculauniversidad.Entidades.Factura
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.SplashActivity
import com.mobileapp_matriculauniversidad.Vista.Administrador.Estudiante.EstudianteAdministrador

class actualizarProfesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador_profesor_actualizar)
        setSupportActionBar(findViewById(R.id.toolbar_profesor_actualizar_administrador))

        cancelar()

        insertar()
    }

    private fun insertar() {
        val cedulaProfesor = findViewById<EditText>(R.id.tv_actualizar_cedula_profesor_admin)
        val nombreProfesor = findViewById<EditText>(R.id.tv_actualizar_nombre_profesor_admin)
        val telefonoProfesor = findViewById<EditText>(R.id.tv_actualizar_telefono_profesor_admin)
        val emailProfesor = findViewById<EditText>(R.id.tv_actualizar_email_profesor_admin)
        val clave = findViewById<EditText>(R.id.tv_actualizar_clave_profesor_admin)

        val p = intent.getSerializableExtra("profesor") as Factura
        cedulaProfesor.setText(p.idFactura)
        nombreProfesor.setText(p.fecha)
        telefonoProfesor.setText(p.tipo)
        emailProfesor.setText(p.moneda)
        clave.setText(p.cantidad)

        val btn_register = findViewById<Button>(R.id.btn_actualizar_profesor)

        btn_register.setOnClickListener {
            if (cedulaProfesor.text.isNotEmpty() || nombreProfesor.text.isNotEmpty() ||
                telefonoProfesor.text.isNotEmpty() || emailProfesor.text.isNotEmpty()
                || clave.text.isNotEmpty()
            ) {
                p.idFactura = cedulaProfesor.text.toString()
                p.fecha = clave.text.toString()
                p.tipo = nombreProfesor.text.toString()
                p.moneda = telefonoProfesor.text.toString()
                p.cantidad = emailProfesor.text.toString().toInt()

                val c = ProfesorAdministrador()
                c.update(p)

                val i = Intent(this, ProfesorAdministrador::class.java)
                startActivity(i)
            }
        }
    }

    private fun cancelar() {
        val btn_cancel = findViewById<Button>(R.id.btn_act_cancelar_profesor)

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

            R.id.item_princ_admin_profesores -> {
                Toast.makeText(applicationContext, "Activity Profesores Administrador", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfesorAdministrador::class.java))
            }
            R.id.item_princ_admin_estudiantes -> {
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