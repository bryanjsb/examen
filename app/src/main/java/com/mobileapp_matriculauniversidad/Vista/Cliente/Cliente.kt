package com.mobileapp_matriculauniversidad.Vista.Cliente

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
import com.mobileapp_matriculauniversidad.Controllers.ControllerFactura
import com.mobileapp_matriculauniversidad.Entidades.Factura
import com.mobileapp_matriculauniversidad.R
import com.mobileapp_matriculauniversidad.SplashActivity
import com.mobileapp_matriculauniversidad.Vista.Administrador.Factura.actualizarProfesor
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*

class Cliente : AppCompatActivity() {

    var controllerFactura: ControllerFactura = ControllerFactura.instance

    lateinit var lista: RecyclerView
    lateinit var adaptador: AdaptadorClienteFactura
    lateinit var profesor: Factura
    var archived = ArrayList<Factura>()
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)
        setSupportActionBar(findViewById(R.id.toolbar_cliente))
        recyclerprofesor()
        buscar()
        obtenerListaProfesor()
        //agregarProfesorActivity()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_cliente, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.item_princ_cliente_facturas -> {
                Toast.makeText(applicationContext, "Activity Estudiantes Administrador", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Cliente::class.java))
            }

            R.id.item_cliente_info -> {
                Toast.makeText(applicationContext, "Saliendo Administrador... ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SplashActivity::class.java))
            }
            R.id.item_cliente_salir -> {
                Toast.makeText(applicationContext, "Informacion", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SplashActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun recyclerprofesor() {
        lista = findViewById(R.id.recycler_listaClienteFactura)
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

                Collections.swap(controllerFactura.listar(), fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {


                position = viewHolder.adapterPosition
                val ptrProfesor: Factura = controllerFactura.listar()[position]
                if (direction == ItemTouchHelper.RIGHT) {
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
                    this@Cliente,
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(
                            this@Cliente,
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


    fun add(profesor: Factura) {
        val i = Intent(this, actualizarProfesor::class.java)
        i.putExtra("profesor", profesor)
        startActivity(i)
    }

    private fun buscar() {
        findViewById<SearchView>(R.id.search_cliente).setOnQueryTextListener(object :
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
        for (p in controllerFactura.listar()) {
            Ncarreras.add(p)
        }
        adaptador = AdaptadorClienteFactura(Ncarreras)
        lista.adapter = adaptador
    }

    fun update(profesor: Factura) {
        for ((cont, p) in controllerFactura.listar().withIndex()) {
            if (p.idFactura.compareTo(profesor.idFactura) == 0) {
                controllerFactura.actualizar(profesor, cont)
            }
        }
    }
}