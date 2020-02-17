package br.com.eduardovpessoa.santacasafernandopolis.ui.main.bed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Bed
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.MainAdapterContract

class BedAdapter(
    private val idUnity: String?,
    private val bedList: MutableList<Bed>?,
    private val bedListener: MainAdapterContract.BedAdapter?
) :
    RecyclerView.Adapter<BedAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(idUnity, bedList?.get(position), position % 2 != 0, bedListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.bed_adapter_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return bedList?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.txtBedName)
        fun bindView(
            idUnity: String?,
            bed: Bed?,
            colored: Boolean,
            bedListener: MainAdapterContract.BedAdapter?
        ) {
            name.text = bed?.name
            if (colored) itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.backgroundDarkGrey
                )
            )
            itemView.setOnClickListener {
                bedListener?.onClickBed(idUnity, bed?.id, bed?.name)
            }
        }
    }
}