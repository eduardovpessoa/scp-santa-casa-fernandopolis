package br.com.eduardovpessoa.santacasafernandopolis.ui.main.unity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Unity

class UnityAdapter(var unityList: MutableList<Unity>?) :
    RecyclerView.Adapter<UnityAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(unityList?.get(position), position % 2 != 0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.unity_adapter_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return unityList?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.txtUnityName)
        fun bindView(unity: Unity?, colored: Boolean) {
            name.text = unity?.name
            if (colored) itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.backgroundDarkGrey
                )
            )
        }
    }
}