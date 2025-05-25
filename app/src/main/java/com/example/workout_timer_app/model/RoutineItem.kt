package com.example.workout_timer_app.model

import java.util.UUID

// 운동, 휴식 클래스를 위한 부모 클래스
sealed class RoutineItem {
    abstract val id: String
}

enum class ExerciseType {  TIME, COUNT }

// 운동 클래스: 이름 + 시간
data class ExerciseItem(
    override val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: ExerciseType,
    val value: Int
) : RoutineItem()

// 휴식 클래스: 기본 시간 30초
data class RestItem(
    override val id: String = UUID.randomUUID().toString(),
    val duration: Int = 30
) : RoutineItem()

// 전체 운동 루틴 클래스: 각 운동 및 휴식 클래스를 리스트로 묶음
data class WorkoutRoutine(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val items: List<RoutineItem>
)