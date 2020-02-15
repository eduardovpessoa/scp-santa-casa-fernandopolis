package br.com.eduardovpessoa.santacasafernandopolis.ui.main

interface MainContract {

    interface View {
        fun initViews()
        fun returnToLogin()
        fun onDestroy()
    }

    interface Presenter {
        fun verifyLoggedIn()
        fun logout()
        fun onDestroy()

    }

}