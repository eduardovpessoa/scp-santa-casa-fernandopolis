package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification

import android.util.Log
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import com.google.firebase.firestore.FirebaseFirestore

class NewClassificationPresenter(var view: NewClassificationContract.View?, var idUnity: String?) :
    NewClassificationContract.Presenter {

    private val db = FirebaseFirestore.getInstance()

    init {
        view?.initViews()
            .run {
                if (!idUnity.isNullOrEmpty()) loadClassification(idUnity)
            }
    }

    override fun loadClassification(idUnity: String?, idBed: String?, idClassification: String?) {
        view?.showMessage("Carregando Classificação...", true)
        db.collection("unity")
            .document(idUnity.toString())
            .collection("bed")
            .document(idBed.toString())
            .collection("classification")
            .document(idClassification.toString())
            .get()
            .addOnSuccessListener { it ->
                view?.dismissMessage()
                    .run { view?.setClassification(it.toObject(Classification::class.java)) }
            }.addOnFailureListener { exception ->
                Log.e(NewClassificationPresenter::class.java.name, exception.message!!)
                view?.showMessage(
                    "Falha ao carregar a classificação! Detalhes: ${exception.message}",
                    false
                )
            }
    }


    override fun saveClassification(
        idUnity: String?,
        idBed: String?,
        classification: Classification?
    ) {
        view?.showMessage("Salvando Classificação...", true)
        db.collection("unity")
            .document(idUnity.toString())
            .collection("bed")
            .document(idBed.toString())
            .collection("classification")
            .document(db.collection("classification").document().id)
            .set(classification)
            .addOnCompleteListener {
                view?.showMessage("Cadastrado com sucesso!", false)
            }.addOnFailureListener { exception ->
                Log.e(NewClassificationPresenter::class.java.name, exception.message!!)
                view?.showMessage(
                    "Falha ao salvar a classificação! Detalhes: ${exception.message}",
                    false
                )
            }
    }

    override fun onDestroy() {
        view = null
    }

}