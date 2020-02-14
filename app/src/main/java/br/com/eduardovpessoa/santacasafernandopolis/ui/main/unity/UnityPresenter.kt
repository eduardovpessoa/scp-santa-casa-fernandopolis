package br.com.eduardovpessoa.santacasafernandopolis.ui.main.unity

import android.util.Log
import br.com.eduardovpessoa.santacasafernandopolis.data.model.Unity
import com.google.firebase.firestore.FirebaseFirestore

class UnityPresenter(var view: UnityContract.View?) : UnityContract.Presenter {

    private val db = FirebaseFirestore.getInstance()
    private var unityList: MutableList<Unity> = arrayListOf()

    init {
        loadUnity()
    }

    override fun loadUnity() {
        db.collection("unity")
            .whereEqualTo("status", true)
            .orderBy("name")
            .get().addOnSuccessListener { it ->
                it.forEach {
                    unityList.add(it.toObject(Unity::class.java))
                }.run {
                    view?.setAdapter(unityList)
                }
            }.addOnFailureListener { exception ->
                Log.e(UnityPresenter::class.java.name, exception.message!!)
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