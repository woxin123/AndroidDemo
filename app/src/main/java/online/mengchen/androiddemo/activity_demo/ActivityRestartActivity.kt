package online.mengchen.androiddemo.activity_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import online.mengchen.androiddemo.R
import kotlinx.android.synthetic.main.activity_restart.*

class ActivityRestartActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ActivityRestartActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restart)
        if (savedInstanceState != null) {
            val test = savedInstanceState.getString("extra_test")
            Log.d(TAG, "[onCreate]restore extra_test: $test")
        }
        btn_activity_restart.setOnClickListener {
            startActivity(Intent(this, ActivityRestartActivity::class.java).apply {
                putExtra("time", System.currentTimeMillis())
            })

        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent, time = $${intent?.getLongExtra("time", 0)}")
    }

    override fun onStart() {
        Log.d(TAG, "[onStart]")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "[onResume]")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "[onPause]")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "[onStop]")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "[onDestroy]")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putString("extra_test", "test")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val test = savedInstanceState.getString("extra_test")
        Log.d(TAG, "[onRestoreInstanceState]restore extra_test: $test")
    }
}
