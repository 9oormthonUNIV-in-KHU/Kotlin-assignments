package com.example.week3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListViewModel : ViewModel() {

    private val _partList =
        MutableStateFlow(listOf("DevRel", "FRONTEND", "BACKEND", "AI", "MOBILE"))
    val partList: StateFlow<List<String>> = _partList

    private val _devrelList = MutableStateFlow(emptyList<Pair<Boolean, String>>())
    val devrelList: StateFlow<List<Pair<Boolean, String>>> = _devrelList

    private val _frontendList = MutableStateFlow(emptyList<Pair<Boolean, String>>())
    val frontendList: StateFlow<List<Pair<Boolean, String>>> = _frontendList

    private val _backendList = MutableStateFlow(emptyList<Pair<Boolean, String>>())
    val backendList: StateFlow<List<Pair<Boolean, String>>> = _backendList

    private val _aiList = MutableStateFlow(emptyList<Pair<Boolean, String>>())
    val aiList: StateFlow<List<Pair<Boolean, String>>> = _aiList

    private val _mobileList = MutableStateFlow(emptyList<Pair<Boolean, String>>())
    val mobileList: StateFlow<List<Pair<Boolean, String>>> = _mobileList

    fun addToList(part: String, todo: String) {
        when (part) {
            "DevRel" -> _devrelList.value += Pair(false, todo)
            "FRONTEND" -> _frontendList.value += Pair(false, todo)
            "BACKEND" -> _backendList.value += Pair(false, todo)
            "AI" -> _aiList.value += Pair(false, todo)
            "MOBILE" -> _mobileList.value += Pair(false, todo)
        }
    }

    fun updateTodoItem(part: String, index: Int) {
        when (part) {
            "DevRel" -> _devrelList.value = _devrelList.value.toMutableList()
                .apply { this[index] = this[index].copy(!this[index].first) }

            "FRONTEND" -> _frontendList.value = _frontendList.value.toMutableList()
                .apply { this[index] = this[index].copy(!this[index].first) }

            "BACKEND" -> _backendList.value = _backendList.value.toMutableList()
                .apply { this[index] = this[index].copy(!this[index].first) }

            "AI" -> _aiList.value = _aiList.value.toMutableList()
                .apply { this[index] = this[index].copy(!this[index].first) }

            "MOBILE" -> _mobileList.value = _mobileList.value.toMutableList()
                .apply { this[index] = this[index].copy(!this[index].first) }
        }
    }

    fun filterList(part: String, todoList: List<Pair<Boolean, String>>) {
        when (part) {
            "DevRel" -> _devrelList.value = todoList
            "FRONTEND" -> _frontendList.value = todoList
            "BACKEND" -> _backendList.value = todoList
            "AI" -> _aiList.value = todoList
            "MOBILE" -> _mobileList.value = todoList
        }
    }

    fun refreshListBoolean() {
        _devrelList.value = _devrelList.value.map { it.copy(false) }
        _frontendList.value = _frontendList.value.map { it.copy(false) }
        _backendList.value = _backendList.value.map { it.copy(false) }
        _aiList.value = _aiList.value.map { it.copy(false) }
        _mobileList.value = _mobileList.value.map { it.copy(false) }
    }
}