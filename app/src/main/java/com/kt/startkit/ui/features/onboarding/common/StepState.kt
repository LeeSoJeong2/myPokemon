package com.kt.startkit.ui.features.onboarding.common

import com.kt.startkit.core.base.ViewState

sealed class StepState: ViewState() {
    object Done: StepState() // 끝남
    object InProgress: StepState() // 진행 중
    object Indexed: StepState() // 예정
}
