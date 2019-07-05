package br.com.wsilva.starwars.features.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.model.dto.PeopleDTO
import kotlinx.android.synthetic.main.lay_home_adapter.view.*

class HomeAdapter(private val context: Context,
                  private val list: List<PeopleDTO>,
                  private val listener: HomeAdapterListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>()
{
    interface HomeAdapterListener {
        fun OnClickListener(user: PeopleDTO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.lay_home_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        holder.lblName.text = entity.name
        holder.lblEspecie.text = context.getString(R.string.app_especie, entity.species)
        holder.lblVehicles.text = context.getString(R.string.app_vehicles, entity.vehicles)
        holder.content.setOnClickListener { listener.OnClickListener(entity) }
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val content = itemView.content
        val lblName = itemView.lblName
        val lblEspecie = itemView.lblEspecie
        val lblVehicles = itemView.lblVehicles
    }
}