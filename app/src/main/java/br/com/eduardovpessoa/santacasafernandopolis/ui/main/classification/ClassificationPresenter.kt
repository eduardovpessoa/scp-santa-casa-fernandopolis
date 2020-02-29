package br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification

import android.util.Log
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Classification
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class ClassificationPresenter(var view: ClassificationContract.View?) :
    ClassificationContract.Presenter {

    private val db = FirebaseFirestore.getInstance()
    private var classificationList: MutableList<Classification> = arrayListOf()
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

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
                    classificationList.add(it.toObject(Classification::class.java))
                }.run {
                    view?.setAdapter(idUnity, idBed, classificationList)
                    val listFiltered = classificationList.filter {
                        sdf.format(it.registered) == sdf.format(Date())
                    }
                    if (listFiltered.isEmpty()) view?.enableBtnNewClassification(true)
                    else view?.enableBtnNewClassification(false)
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

    override fun removeClassification(idUnity: String?, idBed: String?, idClassification: String?) {
        view?.showMessage("Removendo Classificação...", true)
        db.collection("unity")
            .document(idUnity.toString())
            .collection("bed")
            .document(idBed.toString())
            .collection("classification")
            .document(idClassification.toString())
            .update(
                mapOf(
                    "updated" to Date(),
                    "status" to false
                )
            )
            .addOnCompleteListener {
                classificationList.remove(classificationList.find { it.id == idClassification })
                view?.notifyAdapterChanged()
                val listFiltered = classificationList.filter {
                    sdf.format(it.registered) == sdf.format(Date())
                }
                if (listFiltered.isEmpty()) view?.enableBtnNewClassification(true)
                view?.showMessage("Removido com sucesso!", false)
            }.addOnFailureListener { exception ->
                Log.e(ClassificationPresenter::class.java.name, exception.message!!)
                view?.showMessage(
                    "Falha ao remover a Classificação! Detalhes: ${exception.message}",
                    false
                )
            }
    }

    override fun onDestroy() {
        view = null
    }

}