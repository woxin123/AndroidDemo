package online.mengchen.androiddemo.architecture_demo.mvp.presenter

import online.mengchen.androiddemo.architecture_demo.mvp.listener.OnLoginFinishedListener
import online.mengchen.androiddemo.architecture_demo.mvp.model.LoginModel
import online.mengchen.androiddemo.architecture_demo.mvp.model.LoginModelImpl
import online.mengchen.androiddemo.architecture_demo.mvp.view.LoginView

class LoginPresenterImpl(var loginView: LoginView?): LoginPresenter, OnLoginFinishedListener {

    val loginModel: LoginModel = LoginModelImpl()

    override fun validateCredentials(username: String, password: String) {
        loginView?.showProgress()
        loginModel.login(username, password, this)
    }

    override fun onDestory() {
        loginView = null
    }

    override fun onUsernameError() {
        if (loginView != null) {
            loginView?.setUsernameError()
            loginView?.hideProgress()
        }
    }

    override fun onPasswordError() {
        if (loginView != null) {
            loginView?.setPasswordError()
            loginView?.hideProgress()
        }
    }

    override fun onSuccess() {
        if (loginView != null) {
            loginView?.navigateToHome()
        }
    }

}