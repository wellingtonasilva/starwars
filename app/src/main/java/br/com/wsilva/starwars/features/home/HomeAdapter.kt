package br.com.wsilva.starwars.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.model.entity.PeopleEntity
import kotlinx.android.synthetic.main.lay_home_adapter.view.*

class HomeAdapter(private val context: Context,
                  private val list: List<PeopleEntity>,
                  private val listener: HomeAdapterListener) : androidx.recyclerview.widget.RecyclerView.Adapter<HomeAdapter.ViewHolder>()
{
    interface HomeAdapterListener {
        fun OnClickListener(people: PeopleEntity)
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
        holder.lblEspecie.text = context.getString(R.string.app_especie, entity.species ?: context.getString(R.string.app_nao_informado))
        holder.lblVehicles.text = context.getString(R.string.app_vehicles, entity.numberOfVehicles)
        holder.content.setOnClickListener { listener.OnClickListener(entity) }
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)
    {
        val content = itemView.content
        val lblName = itemView.lblName
        val lblEspecie = itemView.lblEspecie
        val lblVehicles = itemView.lblVehicles
    }
}