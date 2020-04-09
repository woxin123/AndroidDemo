package online.mengchen.androiddemo.architecture_demo.mvc.model

import android.content.Context
import online.mengchen.androiddemo.architecture_demo.mvc.bean.Eassy

class EassyModel(val mContext: Context) {

    interface OnEassyListener {
        fun onSuccess(list: MutableList<Eassy>)

        fun onError();
    }

    private var mOnEassyListener: OnEassyListener? = null

    fun getEassy(num: Int, listener: OnEassyListener) {
        this.mOnEassyListener = listener
        val list = mutableListOf<Eassy>()
        val eassy = Eassy()
        if (num != 0) {
            eassy.title = "更新的 title"
        } else {
            eassy.title = "传入的为 0"
        }
        list.add(eassy)
        mOnEassyListener?.onSuccess(list)
    }

}