package br.com.eduardovpessoa.santacasafernandopolis.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.eduardovpessoa.santacasafernandopolis.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(this, this)
    }

    override fun initViews() {
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener { onLoginButtonClicked() }
    }

    override fun onLoginButtonClicked() {
        presenter?.doLogin(
            edtEmail.text.toString().trim(),
            edtPass.text.toString().trim()
        )
    }

    override fun errorInvalidEmail(error: String) {
        edtEmail.requestFocus()
        edtEmail.error = error
    }

    override fun errorInvalidPass(error: String) {
        edtEmail.error = null
        edtPass.requestFocus()
        edtPass.error = error
    }

    override fun showMessage(msg: String, infinite: Boolean) {
        when (infinite) {
            true -> Snackbar.make(
                findViewById(R.id.loginContainer),
                msg,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            else -> Snackbar.make(
                findViewById(R.id.loginContainer),
                msg,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun changeActivity(intent: Intent?) {
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }
}