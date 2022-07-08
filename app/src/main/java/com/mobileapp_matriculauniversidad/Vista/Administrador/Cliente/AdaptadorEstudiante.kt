package com.mobileapp_matriculauniversidad.Vista.Administrador.Cliente

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobileapp_matriculauniversidad.Entidades.Cliente
import com.mobileapp_matriculauniversidad.R
import java.util.*

class AdaptadorEstudiante(private var items: ArrayList<Cliente>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Cliente>? = null

    lateinit var mcontext: Context

    class EstudianteHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val personListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_administrador_estudiante_lista_template, parent, false)
        val sch = EstudianteHolder(personListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.findViewById<TextView>(R.id.textView_estudiante_cedula)?.text = item?.id
        holder.itemView.findViewById<TextView>(R.id.textView_estudiante_nombre)?.text = item?.nombre
        holder.itemView.findViewById<TextView>(R.id.textView_estudiante_telefono)?.text = item?.telefono
        holder.itemView.findViewById<TextView>(R.id.textView_estudiante_email)?.text = item?.correoElectronico
        holder.itemView.findViewById<TextView>(R.id.textView_estudiante_fechaNac)?.text = item?.direccion
        holder.itemView.findViewById<TextView>(R.id.textView_estudiante_carrera)?.text = item?.celular

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Cliente>()
                    for (row in items) {
                        if (row.nombre.lowercase(Locale.getDefault())
                                .contains(charSearch.lowercase(Locale.getDefault()))
                        ) {
                            resultList.add(row)
                        }
                    }
                    itemsList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemsList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsList = results?.values as ArrayList<Cliente>
                notifyDataSetChanged()
            }

        }
    }

}