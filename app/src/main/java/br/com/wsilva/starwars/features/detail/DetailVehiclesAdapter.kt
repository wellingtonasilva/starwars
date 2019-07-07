package br.com.wsilva.starwars.features.detail

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.model.entity.VehiclesEntity
import kotlinx.android.synthetic.main.lay_detail_vehicles_adapter.view.*

class DetailVehiclesAdapter(private val context: Context,
                            private val list: List<VehiclesEntity>,
                            private val listener: Listener) : androidx.recyclerview.widget.RecyclerView.Adapter<DetailVehiclesAdapter.ViewHolder>()
{
    interface Listener {
        fun OnClickListener(vehicle: VehiclesEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.lay_detail_vehicles_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        holder.lblTitle.text = entity.name
        holder.lblTitle.setOnClickListener { listener.OnClickListener(entity) }
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)
    {
        val lblTitle = itemView.lblTitle
    }
}