package br.com.eduardovpessoa.santacasafernandopolis.data.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardovpessoa.santacasafernandopolis.R

class EmptyAdapter : RecyclerView.Adapter<EmptyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.empty_adapter_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }

}