package br.com.eduardovpessoa.santacasafernandopolis.ui.main.bed

import br.com.eduardovpessoa.santacasafernandopolis.data.model.Bed

interface BedContract {
    interface View{
        fun setAdapter(idUnity: String?, bedList : MutableList<Bed>)
        fun showMessage(msg : String, infinite : Boolean)
        fun onDestroy()
    }
    interface Presenter{
        fun loadBed(idUnity : String?)
        fun onDestroy()
    }
}