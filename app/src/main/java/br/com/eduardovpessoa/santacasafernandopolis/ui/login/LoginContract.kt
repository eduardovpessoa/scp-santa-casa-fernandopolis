package br.com.eduardovpessoa.santacasafernandopolis.ui.login

import android.content.Intent

interface LoginContract {
    interface View{
        fun initViews()
        fun onLoginButtonClicked()
        fun errorInvalidEmail(error : String)
        fun errorInvalidPass(error : String)
        fun showMessage(msg : String, infinite : Boolean)
        fun changeActivity(intent : Intent?)
        fun onDestroy()
    }
    interface Presenter{
        fun verifyLoggedIn()
        fun doLogin(email : String, passwd : String)
        fun onDestroy()
    }
}