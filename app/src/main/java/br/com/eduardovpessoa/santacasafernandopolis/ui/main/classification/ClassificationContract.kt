package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification

interface ClassificationContract {
    interface View{
        fun setAdapter(idUnity: String?, idBed : String?, classificationList : MutableList<Classification>)
        fun notifyAdapterChanged()
        fun showMessage(msg : String, infinite : Boolean)
        fun dismissMessage()
        fun enableBtnNewClassification(show : Boolean)
        fun onDestroy()
    }
    interface Presenter{
        fun loadClassification(idUnity : String?, idBed : String?)
        fun removeClassification(idUnity : String?, idBed : String?, idClassification : String?)
        fun onDestroy()
    }
}