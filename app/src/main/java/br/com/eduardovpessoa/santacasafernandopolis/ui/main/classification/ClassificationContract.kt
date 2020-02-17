package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification

interface ClassificationContract {
    interface View{
        fun setAdapter(idUnity: String?, idBed : String?, classificationList : MutableList<Classification>)
        fun showMessage(msg : String, infinite : Boolean)
        fun onDestroy()
    }
    interface Presenter{
        fun loadClassification(idUnity : String?, idBed : String?)
        fun onDestroy()
    }
}