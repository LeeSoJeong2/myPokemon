package com.kt.startkit.ui.features.route

import com.kt.startkit.core.base.ViewState

// State Hierarchy
//
// RouteState
//    |- Loading: 초기 상태
//    |- FailToInitialize : 서버 데이터 패치 오류
//    |- Success : splash 종료 후 다음 화면으로 이동 <abstract>
//        |- NavigateToMain : 메인 화면 이동
//        |- NavigateToOnBoarding : 온보딩 화면 이동


sealed class RouteState : ViewState() {
    object Loading : RouteState()

    object FailToInitialize : RouteState()

    abstract class Success : RouteState()

    object NavigateToMain : Success()

    object NavigateToOnBoarding : Success()
}