package online.mengchen.androiddemo.customer_view_demo.common

import androidx.collection.ArrayMap


object Constant {

    const val APP_PRACTISE = "Practice"
    const val COUNTER = "Click Count"
    const val CIRCLE = "Circle"
    const val TEST_BUTTON = "TestButton"

    const val APP_GET = "GET"
    const val GET_CUSTOM_PUBLISH = "发布内容弹出效果"

    //马蜂窝旅游
    const val APP_MFW = "马蜂窝旅游"
    const val MFW_WRIGGLE_TAB = "蠕动tab"

    //支付宝
    const val APP_ZFB = "支付宝"
    const val ZFB_SESAME_CREDIT = "芝麻信用"

    //微信
    const val APP_WE_CHAT = "微信"
    const val WE_CHAT_RADAR_ADD_FRIENDS = "雷达加朋友"

    //虾米音乐ø
    const val APP_XIA_MI_MUSIC = "虾米音乐"
    const val XIA_MI_MUSIC_PLAY_PROGRESSBAR = "播放进度条"

    // 滑动冲突
    const val APP_SLIDE_CONFLICAT = "滑动冲突"
    const val SLIDE_CONFLICT_DEMO1 = "滑动冲突 Demo1"
    const val SLIDE_CONFLICT_DEMO2 = "滑动冲突 Demo2"

    val APP_MAP = ArrayMap<String, ArrayList<String>>()

    init {
        with(APP_MAP) {
            put(APP_PRACTISE, arrayListOf(COUNTER, CIRCLE, TEST_BUTTON))
            put(APP_GET, arrayListOf(GET_CUSTOM_PUBLISH))
            put(APP_MFW, arrayListOf(MFW_WRIGGLE_TAB))
            put(APP_ZFB, arrayListOf(ZFB_SESAME_CREDIT))
            put(APP_WE_CHAT, arrayListOf(WE_CHAT_RADAR_ADD_FRIENDS))
            put(APP_XIA_MI_MUSIC, arrayListOf(XIA_MI_MUSIC_PLAY_PROGRESSBAR))
            put(APP_SLIDE_CONFLICAT, arrayListOf(SLIDE_CONFLICT_DEMO1, SLIDE_CONFLICT_DEMO2))
        }
    }
}