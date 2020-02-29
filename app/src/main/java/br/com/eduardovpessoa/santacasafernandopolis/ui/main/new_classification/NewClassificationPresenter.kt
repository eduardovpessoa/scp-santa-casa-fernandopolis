package br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification

import android.util.Log
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class NewClassificationPresenter(
    var view: NewClassificationContract.View?,
    private var idUnity: String?,
    private var idBed: String?,
    private var idClassification: String?
) :
    NewClassificationContract.Presenter {

    private val db = FirebaseFirestore.getInstance()

    init {
        if (!idClassification.isNullOrEmpty()) {
            loadClassification()
        }
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
            .addOnSuccessListener {
                val classification = it.toObject(Classification::class.java)
                view?.changeTitle(
                    "SCP - ${SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale("pt", "BR")
                    ).format(classification?.registered as Date)}"
                )
                view?.setClassification(classification)
                view?.dismissMessage()
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
                view?.goBack()
            }.addOnFailureListener { exception ->
                Log.e(NewClassificationPresenter::class.java.name, exception.message!!)
                view?.showMessage(
                    "Falha ao salvar a Classificação! Detalhes: ${exception.message}",
                    false
                )
            }
    }

    override fun updateClassification(classification: Classification?) {
        view?.showMessage("Atualizando Classificação...", true)
        db.collection("unity")
            .document(idUnity.toString())
            .collection("bed")
            .document(idBed.toString())
            .collection("classification")
            .document(classification?.id.toString())
            .update(
                mapOf(
                    "alimentacao" to classification?.alimentacao,
                    "cuidadoCorporal" to classification?.cuidadoCorporal,
                    "curativo" to classification?.curativo,
                    "deambulacao" to classification?.deambulacao,
                    "eliminacao" to classification?.eliminacao,
                    "estadoMental" to classification?.estadoMental,
                    "integridade" to classification?.integridade,
                    "mobilidade" to classification?.mobilidade,
                    "oxigenacao" to classification?.oxigenacao,
                    "updated" to Date(),
                    "sinaisVitais" to classification?.sinaisVitais,
                    "tempoCurativo" to classification?.tempoCurativo,
                    "terapeutica" to classification?.terapeutica,
                    "nutricao" to classification?.nutricao,
                    "umidade" to classification?.umidade,
                    "totalBraden" to classification?.totalBraden,
                    "totalFugulin" to classification?.totalFugulin
                )
            )
            .addOnCompleteListener {
                view?.showMessage("Atualizado com sucesso!", false)
                view?.goBack()
            }.addOnFailureListener { exception ->
                Log.e(NewClassificationPresenter::class.java.name, exception.message!!)
                view?.showMessage(
                    "Falha ao atualizar a Classificação! Detalhes: ${exception.message}",
                    false
                )
            }
    }

    override fun onDestroy() {
        view = null
    }

}