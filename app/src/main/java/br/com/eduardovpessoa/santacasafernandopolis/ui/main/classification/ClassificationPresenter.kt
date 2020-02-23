package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

import android.util.Log
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import com.google.firebase.firestore.FirebaseFirestore

class ClassificationPresenter(var view: ClassificationContract.View?) :
    ClassificationContract.Presenter {

    private val db = FirebaseFirestore.getInstance()
    private var classificationList: MutableList<Classification> = arrayListOf()

    override fun loadClassification(idUnity: String?, idBed: String?) {
        view?.showMessage("Carregando Classificações...", true)
        db.collection("unity")
            .document(idUnity.toString())
            .collection("bed")
            .document(idBed.toString())
            .collection("classification")
            .whereEqualTo("status", true)
            .get().addOnSuccessListener { it ->
                it.forEach {
                    for (i in 1..30) {
                        classificationList.add(it.toObject(Classification::class.java))
                    }
                }.run {
                    view?.setAdapter(idUnity, idBed, classificationList)
                    view?.dismissMessage()
                }
            }.addOnFailureListener { exception ->
                Log.e(ClassificationPresenter::class.java.name, exception.message!!)
                view?.showMessage(
                    "Falha ao carregar as unidades! Detalhes: ${exception.message}",
                    false
                )
            }
    }

    override fun onDestroy() {
        view = null
    }

}