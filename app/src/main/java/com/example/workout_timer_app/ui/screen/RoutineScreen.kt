package com.example.workout_timer_app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workout_timer_app.model.*
import com.example.workout_timer_app.viewmodel.RoutineViewModel
import kotlinx.coroutines.delay

@Composable
fun RoutineScreen(viewModel: RoutineViewModel = viewModel()) {
    val routine by viewModel.routine.collectAsState()
    val currentItem by viewModel.currentItem.collectAsState()
    val isFinished by viewModel.isFinished.collectAsState()

    LaunchedEffect(Unit) {
        if (routine == null) {
            viewModel.createRoutine(
                title = "상체 루틴",
                exercises = listOf(
                    ExerciseItem(name = "푸시업", type = ExerciseType.COUNT, value = 10),
                    ExerciseItem(name = "플랭크", type = ExerciseType.TIME, value = 30)
                )
            )
        }
    }

    Column(modifier = Modifier.padding(24.dp)) {
        when {
            isFinished -> {
                Text("루틴이 완료되었습니다!")
            }

            currentItem == null -> {
                Text("루틴을 준비 중입니다...")
            }

            else -> {
                currentItem?.let { item ->
                    when (item) {
                        is ExerciseItem -> {
                            Text("운동: ${item.name}")
                            when (item.type) {
                                ExerciseType.TIME -> {
                                    Text("진행 시간: ${item.value}초")

                                    LaunchedEffect(key1 = item.id) {
                                        delay(item.value * 1000L)
                                        viewModel.nextItem()
                                    }
                                }
                                ExerciseType.COUNT -> {
                                    Text("횟수: ${item.value}회")
                                    Button(onClick = { viewModel.nextItem() }) {
                                        Text("완료")
                                    }
                                }
                            }
                        }
                        is RestItem -> {
                            Text("휴식: ${item.duration}초")

                            LaunchedEffect(key1 = item.id) {
                                delay(item.duration * 1000L)
                                viewModel.nextItem()
                            }
                        }
                    }
                }
            }
        }
    }
}