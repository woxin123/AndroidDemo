package online.mengchen.androiddemo.activity_demo.moniter_demo

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class ActivityMonitor(app: Application) {

    companion object {
        const val TAG = "ActivityMonitor"
    }

    init {
        app.registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                Log.d(TAG, "activity: ${activity.componentName} Paused")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(TAG, "activity: ${activity.componentName} Start")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(TAG, "activity: ${activity.componentName} Destroy")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(TAG, "activity: ${activity.componentName} Stop")
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(TAG, "activity: ${activity.componentName} Create")
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(TAG, "activity: ${activity.componentName} Resume")
            }

        })
    }

}