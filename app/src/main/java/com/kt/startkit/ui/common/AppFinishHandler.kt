package com.kt.startkit.ui.common

import android.content.Context
import android.widget.Toast
import com.kt.startkit.R
import com.kt.startkit.util.findActivity

class AppFinishHandler {

    var backButtonPressedTime: Long? = null

    companion object {
        private const val APP_FINISH_DURATION = 2000 // 2초
    }

    fun onWillPop(context: Context): Boolean {
        // 2초안에 이전 버튼을 두 번 눌렀을 경우
        if (backButtonPressedTime != null && (backButtonPressedTime!! + APP_FINISH_DURATION) > System.currentTimeMillis()) {
            // 앱종료
            context.findActivity()?.finish()
            return true
        }

        // 이전 버튼을 2초 전에 누르지 않았 다면 toast 표출.
        else {
            backButtonPressedTime = System.currentTimeMillis()
            Toast.makeText(
                context,
                context.getString(R.string.finish_app),
                Toast.LENGTH_LONG
            ).show()

            return false
        }
    }
}