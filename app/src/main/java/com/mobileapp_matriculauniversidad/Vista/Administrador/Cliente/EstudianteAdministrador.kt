package com.mobileapp_matriculauniversidad.Vista.Administrador.Cliente

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
import com.mobileapp_matriculauniversidad.Controllers.ControllerCliente
import com.mobileapp_matriculauniversidad.Entidades.Cliente
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.SplashActivity
import com.mobileapp_matriculauniversidad.Vista.Administrador.Factura.ProfesorAdministrador
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*

class EstudianteAdministrador : AppCompatActivity() {

    var controllerCliente: ControllerCliente = ControllerCliente.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador: AdaptadorEstudiante
    lateinit var estudiante: Cliente
    var archived = ArrayList<Cliente>()
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador_estudiante)
        setSupportActionBar(findViewById(R.id.toolbar_estudiante_administrador))
        recyclerEstudiante()
        buscar()
        obtenerListaEstudiante()
        agregarEstudianteActivity()


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

    private fun recyclerEstudiante() {
        lista = findViewById(R.id.recycler_listaEstudiantes_administrador)
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

                Collections.swap(controllerCliente.listar(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition
                val ptrEstudiante: Cliente = controllerCliente.listar()[position]
                if (direction == ItemTouchHelper.LEFT) {
                    estudiante = ptrEstudiante
                    controllerCliente.eliminar(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, estudiante.nombre + "Se eliminar??a...", Snackbar.LENGTH_LONG)
                        .setAction("Undo") {
                            controllerCliente.listar().add(position, estudiante)
                            lista.adapter?.notifyItemInserted(position)
                        }.show()
                    adaptador = AdaptadorEstudiante(controllerCliente.listar())
                    lista.adapter = adaptador
                } else {
                    estudiante = ptrEstudiante
                    archived.add(estudiante)
                    add(estudiante)
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
                    this@EstudianteAdministrador,
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@EstudianteAdministrador, R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(
                            this@EstudianteAdministrador,
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

    private fun agregarEstudianteActivity() {
        val add: FloatingActionButton = findViewById(R.id.btn_agregar_estudiante_administrador)
        add.setOnClickListener {
            val i = Intent(this, InsertarEstudiante::class.java)
            startActivity(i)
        }
    }

    private fun buscar() {
        findViewById<SearchView>(R.id.search_estudiante_administrador).setOnQueryTextListener(object :
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

    private fun obtenerListaEstudiante() {
        val Nestudiantes = ArrayList<Cliente>()
        for (p in controllerCliente.listar()) {
            Nestudiantes.add(p)
        }
        adaptador = AdaptadorEstudiante(Nestudiantes)
        lista.adapter = adaptador
    }


    fun add(estudiante: Cliente) {
        val i = Intent(this, actualizarEstudiante::class.java)
        i.putExtra("estudiante", estudiante)
        startActivity(i)
    }

    fun update(estudiante: Cliente) {
        for ((cont, p) in controllerCliente.listar().withIndex()) {
            if (p.id.compareTo(estudiante.id) == 0) {
                controllerCliente.actualizar(estudiante, cont)
            }
        }
    }
}


