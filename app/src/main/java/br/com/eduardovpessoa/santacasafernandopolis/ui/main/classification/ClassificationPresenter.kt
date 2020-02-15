package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

import br.com.eduardovpessoa.santacasafernandopolis.data.model.Bed
import com.google.firebase.firestore.FirebaseFirestore

class ClassificationPresenter(var view : ClassificationContract.View?) : ClassificationContract.Presenter {

    private val db = FirebaseFirestore.getInstance()
    private var bedList: MutableList<Bed> = arrayListOf()

    init {
        loadBed()
    }

    override fun loadBed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}