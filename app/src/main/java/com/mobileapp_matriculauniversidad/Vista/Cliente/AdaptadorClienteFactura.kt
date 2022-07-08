package com.mobileapp_matriculauniversidad.Vista.Cliente

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobileapp_matriculauniversidad.Entidades.Factura
import com.mobileapp_matriculauniversidad.R
import java.util.*

class AdaptadorClienteFactura(private var items: ArrayList<Factura>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    var itemsList: ArrayList<Factura>? = null

    lateinit var mcontext: Context

    class FacturaHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val personListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_administrador_profesor_lista_template, parent, false)
        val sch = FacturaHolder(personListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.itemView.findViewById<TextView>(R.id.textView_id_Factura)?.text = item?.idFactura
        holder.itemView.findViewById<TextView>(R.id.tv_factura_fecha)?.text = item?.fecha
        holder.itemView.findViewById<TextView>(R.id.tv_factura_tipo)?.text = item?.tipo
        holder.itemView.findViewById<TextView>(R.id.tv_factura_moneda)?.text = item?.moneda

        holder.itemView.findViewById<TextView>(R.id.tv_factura_cantidad)?.text = item?.cantidad.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_factura_descripcion)?.text = item?.descripcion
        holder.itemView.findViewById<TextView>(R.id.tv_factura_precioUni)?.text = item?.precioUnitario.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_factura_importe)?.text = item?.importe.toString()


    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Factura>()
                    for (row in items) {
                        if (row.idFactura.lowercase(Locale.getDefault())
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
                itemsList = results?.values as ArrayList<Factura>
                notifyDataSetChanged()
            }

        }
    }

}