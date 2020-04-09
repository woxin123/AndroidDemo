package online.mengchen.androiddemo.architecture_demo.mvp.listener

interface OnLoginFinishedListener {
    fun onUsernameError()
    fun onPasswordError()
    fun onSuccess()
}