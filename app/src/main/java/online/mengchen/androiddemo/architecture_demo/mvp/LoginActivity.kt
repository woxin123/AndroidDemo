package online.mengchen.androiddemo.architecture_demo.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import online.mengchen.androiddemo.R
import online.mengchen.androiddemo.architecture_demo.mvp.presenter.LoginPresenter
import online.mengchen.androiddemo.architecture_demo.mvp.presenter.LoginPresenterImpl
import online.mengchen.androiddemo.architecture_demo.mvp.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginView {

    private val username by lazy { findViewById<EditText>(R.id.architecture_mvp_username) }
    private val password by lazy { findViewById<EditText>(R.id.architecture_mvp_password) }
    private val progress by lazy { findViewById<ProgressBar>(R.id.architecture_mvp_progress) }
    private val presenter: LoginPresenter = LoginPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        R.id.architecture_mvp_login.run {
            architecture_mvp_login.setOnClickListener(this@LoginActivity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        presenter.validateCredentials(username.text.toString(), password.text.toString())
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun setUsernameError() {
        username.setError(getString(R.string.username_error))
    }

    override fun setPasswordError() {
        password.setError(getString(R.string.password_error))
    }

    override fun navigateToHome() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
    }
}
