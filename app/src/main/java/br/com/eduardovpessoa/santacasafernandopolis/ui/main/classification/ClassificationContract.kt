package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

import br.com.eduardovpessoa.santacasafernandopolis.data.model.Bed

interface ClassificationContract {
    interface View{
        fun setAdapter(bedList : MutableList<Bed>)
        fun showMessage(msg : String, infinite : Boolean)
        fun onDestroy()
    }
    interface Presenter{
        fun loadBed()
        fun onDestroy()
    }
}