package br.com.eduardovpessoa.santacasafernandopolis.ui.login

interface LoginContract {
    interface View{
        fun initViews()
        fun onLoginButtonClicked()
        fun errorInvalidEmail(error : String)
        fun errorInvalidPass(error : String)
        fun showMessage(msg : String, infinite : Boolean)
        fun changeActivity(id : String?, email : String?)
        fun onDestroy()
    }
    interface Presenter{
        fun verifyLoggedIn()
        fun doLogin(email : String, passwd : String)
        fun onDestroy()
    }
}