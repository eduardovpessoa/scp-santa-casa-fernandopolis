package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification

import android.util.Log
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import com.google.firebase.firestore.FirebaseFirestore

class NewClassificationPresenter(
    var view: NewClassificationContract.View?,
    var idUnity: String?,
    var idBed: String?,
    var idClassification: String?
) :
    NewClassificationContract.Presenter {

    private val db = FirebaseFirestore.getInstance()

    init {
        if (!idClassification.isNullOrEmpty())
            loadClassification()
        view?.initViews()
    }

    override fun loadClassification() {
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
        classification: Classification?
    ) {
        view?.showMessage("Salvando Classificação...", true)
        db.collection("unity")
            .document(idUnity.toString())
            .collection("bed")
            .document(idBed.toString())
            .collection("classification")
            .document()
            .set(classification as Classification)
            .addOnCompleteListener {
                view?.showMessage("Cadastrado com sucesso!", false)
            }.addOnFailureListener { exception ->
                Log.e(NewClassificationPresenter::class.java.name, exception.message!!)
                view?.showMessage(
                    "Falha ao salvar a Classificação! Detalhes: ${exception.message}",
                    false
                )
            }
    }

    override fun onDestroy() {
        view = null
    }

}