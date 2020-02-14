package br.com.eduardovpessoa.santacasafernandopolis.ui.main.unity

import br.com.eduardovpessoa.santacasafernandopolis.data.model.Unity

interface UnityContract {
    interface View{
        fun setAdapter(unityList : MutableList<Unity>)
        fun showMessage(msg : String, infinite : Boolean)
        fun onDestroy()
    }
    interface Presenter{
        fun loadUnity()
        fun onDestroy()
    }
    interface Adapter {

    }
}