package br.com.eduardovpessoa.santacasafernandopolis.ui.main.bed

import android.util.Log
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Bed
import com.google.firebase.firestore.FirebaseFirestore

class BedPresenter(var view: BedContract.View?) : BedContract.Presenter {

    private val db = FirebaseFirestore.getInstance()
    private var bedList: MutableList<Bed> = arrayListOf()

    override fun loadBed(idUnity: String?) {
        db.collection("unity")
            .document(idUnity.toString())
            .collection("bed")
            .orderBy("cod")
            .get().addOnSuccessListener { it ->
                it.forEach {
                    bedList.add(it.toObject(Bed::class.java))
                }.run {
                    view?.setAdapter(idUnity, bedList)
                }
            }.addOnFailureListener { exception ->
                Log.e(BedPresenter::class.java.name, exception.message!!)
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