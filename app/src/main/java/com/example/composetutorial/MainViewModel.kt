package com.example.composetutorial

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddTaskUiState(
    val tasks: MutableList<String> = arrayListOf(),
    val newTask: String = "",
    val isCompleted: Boolean = false
)

@HiltViewModel
class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState.asStateFlow()

    fun newTaskChanged(value: String) {
        _uiState.update {
            it.copy(newTask = value)
        }
    }

    fun addTask() {
        _uiState.update {
            it.tasks.add(uiState.value.newTask)
            it
        }
    }
}