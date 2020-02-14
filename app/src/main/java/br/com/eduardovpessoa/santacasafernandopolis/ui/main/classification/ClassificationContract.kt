package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

interface ClassificationContract {
    interface View{
        fun initViews()
        fun showMessage(msg : String, infinite : Boolean)
        fun onDestroy()
    }
    interface Presenter{
        fun onDestroy()
    }
}