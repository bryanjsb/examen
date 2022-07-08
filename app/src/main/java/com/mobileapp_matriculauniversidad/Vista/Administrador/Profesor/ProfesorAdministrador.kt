package com.mobileapp_matriculauniversidad.Vista.Administrador.Profesor

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.mobileapp_matriculauniversidad.Controllers.ControllerProfesor
import com.mobileapp_matriculauniversidad.Entidades.Factura
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.SplashActivity
import com.mobileapp_matriculauniversidad.Vista.Administrador.Estudiante.EstudianteAdministrador
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*

class ProfesorAdministrador : AppCompatActivity() {

    var controllerProfesor: ControllerProfesor = ControllerProfesor.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador: AdaptadorProfesor
    lateinit var profesor: Factura
    var archived = ArrayList<Factura>()
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador_profesor)
        setSupportActionBar(findViewById(R.id.toolbar_profesor_administrador))
        recyclerprofesor()
        buscar()
        obtenerListaProfesor()
        agregarProfesorActivity()


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

    private fun recyclerprofesor() {
        lista = findViewById(R.id.recycler_listaProfesores_administrador)
        lista.layoutManager = LinearLayoutManager(lista.context)
        lista.setHasFixedSize(true)


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(controllerProfesor.listar(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition
                val ptrProfesor: Factura = controllerProfesor.listar()[position]
                if (direction == ItemTouchHelper.LEFT) {
                    profesor = ptrProfesor
                    controllerProfesor.eliminar(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, profesor.idFactura + "Se eliminaría...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        controllerProfesor.listar().add(position, profesor)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = AdaptadorProfesor(controllerProfesor.listar())
                    lista.adapter = adaptador
                } else {
                    profesor = ptrProfesor
                    archived.add(profesor)
                    add(profesor)
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.Builder(
                    this@ProfesorAdministrador,
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(
                            this@ProfesorAdministrador,
                            R.color.red
                        )
                    )
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(
                            this@ProfesorAdministrador,
                            R.color.green
                        )
                    )
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        }


        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lista)
    }

    private fun agregarProfesorActivity() {
        val add: FloatingActionButton = findViewById(R.id.btn_agregar_profesores_administrador)
        add.setOnClickListener {
            val i = Intent(this, InsertarProfesor::class.java)
            startActivity(i)
        }
    }

    private fun buscar() {
        findViewById<SearchView>(R.id.search_profesores_administrador).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return false
            }
        })
    }

    private fun obtenerListaProfesor() {
        val Ncarreras = ArrayList<Factura>()
        for (p in controllerProfesor.listar()) {
            Ncarreras.add(p)
        }
        adaptador = AdaptadorProfesor(Ncarreras)
        lista.adapter = adaptador
    }


    fun add(profesor: Factura) {
        val i = Intent(this, actualizarProfesor::class.java)
        i.putExtra("profesor", profesor)
        startActivity(i)
    }

    fun update(profesor: Factura) {
        for ((cont, p) in controllerProfesor.listar().withIndex()) {
            if (p.idFactura.compareTo(profesor.idFactura) == 0) {
                controllerProfesor.actualizar(profesor, cont)
            }
        }
    }
}