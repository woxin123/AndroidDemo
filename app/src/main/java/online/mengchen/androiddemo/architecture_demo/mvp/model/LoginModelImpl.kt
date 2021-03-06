package online.mengchen.androiddemo.architecture_demo.mvp.model

import android.text.TextUtils
import online.mengchen.androiddemo.architecture_demo.mvp.listener.OnLoginFinishedListener

class LoginModelImpl: LoginModel {

    override fun login(
        username: String,
        password: String,
        listener: OnLoginFinishedListener
    ) {
        var error = false
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError()
            error = true
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError()
            error = true
        }
        if (!error) {
            listener.onSuccess()
        }
    }
}