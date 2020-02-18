package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification

class NewClassificationPresenter(var view: NewClassificationContract.View?) :
    NewClassificationContract.Presenter {

    init {

    }

    override fun loadClassification(idUnity: String?, idBed: String?, idClassification: String?) {

    }

    override fun onDestroy() {
        view = null
    }


}