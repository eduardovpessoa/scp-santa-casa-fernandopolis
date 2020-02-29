package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.MainAdapterContract

class ClassificationAdapter(
    private val idUnity: String?,
    private val idBed: String?,
    private val classificationList: MutableList<Classification>?,
    private val classificationListener: MainAdapterContract.ClassificationAdapter?
) :
    RecyclerView.Adapter<ClassificationAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(
            idUnity,
            idBed,
            classificationList?.get(position),
            classificationListener
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.classification_adapter_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return classificationList?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.txtClassificationName)
        fun bindView(
            idUnity: String?,
            idBed: String?,
            classification: Classification?,
            classificationListener: MainAdapterContract.ClassificationAdapter?
        ) {
            name.text = classification?.toString()
            itemView.setOnClickListener {
                classificationListener?.onClickClassification(
                    idUnity,
                    idBed,
                    classification?.id,
                    classification?.registered?.time
                )
            }
            itemView.setOnLongClickListener {
                val dialogBuilder = AlertDialog.Builder(itemView.context)

                dialogBuilder.setMessage("Deseja remover a classificação?")
                    .setCancelable(false)
                    .setPositiveButton("Sim!") { _, _ ->
                        classificationListener?.onLongClickClassification(
                            idUnity,
                            idBed,
                            classification?.id
                        )
                    }
                    .setNegativeButton("Não") { dialog, _ -> dialog.cancel() }

                val alert = dialogBuilder.create()
                alert.setTitle("Alerta")
                alert.show()
                true
            }
        }
    }

}