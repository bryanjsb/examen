package com.mobileapp_matriculauniversidad.Vista.Administrador.Estudiante


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp_matriculauniversidad.Entidades.Cliente
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.SplashActivity
import com.mobileapp_matriculauniversidad.Vista.Administrador.Profesor.ProfesorAdministrador

class actualizarEstudiante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador_estudiante_actualizar)
        setSupportActionBar(findViewById(R.id.toolbar_estudiante_actualizar_administrador))

        cancelar()
        insertar()
    }

    private fun insertar() {
        val p = intent.getSerializableExtra("estudiante") as Cliente

        val cedula = findViewById<EditText>(R.id.tv_actualizar_cedula_estudiante_admin)
        val nombre = findViewById<EditText>(R.id.tv_actualizar_nombre_estudiante_admin)
        val telefono = findViewById<EditText>(R.id.tv_actualizar_telefono_estudiante_admin)
        val email = findViewById<EditText>(R.id.tv_actualizar_email_estudiante_admin)
        val date = findViewById<EditText>(R.id.tv_actualizar_date_estudiante_admin)
        val carrera = findViewById<EditText>(R.id.tv_actualizar_carrera_estudiante_admin)

        cedula.setText(p.id)
        nombre.setText(p.nombre)
        telefono.setText(p.telefono.toString())
        email.setText(p.correoElectronico)
        date.setText(p.direccion)
        carrera.setText(p.telefono)
        val btn_register = findViewById<Button>(R.id.btn_act_estudiante)


        btn_register.setOnClickListener {
            if (nombre.text.isNotEmpty() || cedula.text.isNotEmpty() || telefono.text.isNotEmpty()
                || email.text.isNotEmpty() || date.text.isNotEmpty() || carrera.text.isNotEmpty()
            ) {
                p.id = cedula.text.toString()
                p.nombre = nombre.text.toString()
                p.telefono = telefono.text.toString()
                p.correoElectronico = email.text.toString()
                p.direccion = date.text.toString()
                p.telefono = carrera.text.toString()

                val c = EstudianteAdministrador()
                c.update(p)

                val i = Intent(this, EstudianteAdministrador::class.java)
                startActivity(i)
            }
        }
    }

    private fun cancelar() {
        val btn_cancel = findViewById<Button>(R.id.btn_act_cancelar_estudiante)

        btn_cancel.setOnClickListener {
            val i = Intent(this, EstudianteAdministrador::class.java)
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