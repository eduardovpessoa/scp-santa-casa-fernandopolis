package br.com.eduardovpessoa.santacasafernandopolis.ui.login

import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(var view: LoginContract.View?) :
    LoginContract.Presenter {

    private var fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        verifyLoggedIn()
        view?.initViews()
    }

    override fun verifyLoggedIn() {
        if (fbAuth.currentUser != null) {
            view?.changeActivity(fbAuth.currentUser?.uid, fbAuth.currentUser?.uid)
        }
    }

    override fun doLogin(email: String, passwd: String) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.errorInvalidEmail("E-mail inválido!")
            return
        }
        if (passwd.isEmpty() || passwd.length < 8) {
            view?.errorInvalidPass("A senha deve possuir no mínimo 8 caracteres!")
            return
        }

        view?.showMessage("Autenticando...", true)
        fbAuth.signInWithEmailAndPassword(email, passwd)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view?.showMessage("Sucesso!", false)
                    verifyLoggedIn()
                } else {
                    view?.showMessage("Erro: ${task.exception?.message}", false)
                }
            }
    }

    override fun onDestroy() {
        view = null
    }

}