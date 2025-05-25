package com.example.workout_timer_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import com.example.workout_timer_app.model.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

// 운동 루틴 생성 및 상태 관리 클래스
class RoutineViewModel : ViewModel() {
    // 로직용 변수 (클래스 내부에서만 조작 가능)
    private val _routine = MutableStateFlow<WorkoutRoutine?>(null)
    // 읽기 전용 변수 (외부에서 접근하는 변수)
    val routine: StateFlow<WorkoutRoutine?> = _routine

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex

    val currentItem: StateFlow<RoutineItem?> = combine(
        routine, currentIndex
    ) { routineValue, index ->
        routineValue?.items?.getOrNull(index)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val isFinished: StateFlow<Boolean> = combine(routine, currentIndex) { routineValue, index ->
        val total = routineValue?.items?.size ?: return@combine false
        index >= total || index == -1
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // 운동 루틴 생성
    fun createRoutine(title: String, exercises: List<ExerciseItem>) {
        // 운동 루틴용 빈 리스트
        val fullList = mutableListOf<RoutineItem>()
        // exercises의 모든 item에 대해 반복
        exercises.forEachIndexed { index, item ->
            // fullList에 item을 삽입
            fullList.add(item)
            // 마지막 item이 아니라면 RestItem 추가
            if (index < exercises.lastIndex) {
                fullList.add(RestItem())
            }
        }
        // _routine 업데이트 (routine도 자동 업데이트)
        _routine.value = WorkoutRoutine(title = title, items = fullList)
    }

    fun nextItem() {
        val next = _currentIndex.value + 1
        val total = _routine.value?.items?.size ?: return

        if (next < total) {
            _currentIndex.value = next
        } else {
            _currentIndex.value = -1
        }
    }
}