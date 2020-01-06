package br.com.eduardovpessoa.santacasafernandopolis.ui.main

import com.google.firebase.auth.FirebaseAuth

class MainPresenter(var view: MainContract.View?) : MainContract.Presenter {

    private var fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        verifyLoggedIn()
        view?.initViews()
    }

    override fun verifyLoggedIn() {
        if (fbAuth.currentUser == null) {
            view?.returnToLogin()
        }
    }

    override fun logout() {
        fbAuth.signOut()
    }

    override fun onDestroy() {
        view = null
    }

}