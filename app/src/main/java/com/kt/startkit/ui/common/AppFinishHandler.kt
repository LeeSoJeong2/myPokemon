package com.kt.startkit.ui.common

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.kt.startkit.R
import com.kt.startkit.util.findActivity
import java.time.LocalDateTime


class AppFinishHandler {
    var backButtonPressedTime: LocalDateTime? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun onWillPop(context: Context): Boolean {
        // 2초안에 이전 버튼을 두 번 눌렀을 경우
        if (backButtonPressedTime != null && backButtonPressedTime!!.plusSeconds(2) > LocalDateTime.now()) {
            // 앱종료
            context.findActivity()?.finish()
            return true
        }

        // 이전 버튼을 2초 전에 누르지 않았 다면 toast 표출.
        else {
            backButtonPressedTime = LocalDateTime.now()
            Toast.makeText(
                context,
                context.getString(R.string.finish_app),
                Toast.LENGTH_LONG
            ).show()

            return false
        }
    }
}