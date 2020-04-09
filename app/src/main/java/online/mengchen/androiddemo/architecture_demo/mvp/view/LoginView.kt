package online.mengchen.androiddemo.architecture_demo.mvp.view

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToHome()
}