package com.kt.startkit.ui.features.start

import com.kt.startkit.core.base.ViewState

// State Hierarchy
//
// StartState
//    |- Loading: 초기 상태
//    |- FailToInitialize : 서버 데이터 패치 오류
//    |- ShouldAppUpdate : 강제 업데이트 필요
//    |- Success : splash 종료 후 다음 화면으로 이동 <abstract>
//        |- NeedToLogin : 로그인 화면 이동
//        |- NavigateToRouteScreen : Route 화면 이동

sealed class StartState : ViewState() {
    object Loading : StartState()
    object FailToInitialize : StartState()
    object ShouldAppUpdate : StartState()

    abstract class Success : StartState()

    object NeedToLogin : Success()
    object NavigateToRouteScreen : Success()
}

