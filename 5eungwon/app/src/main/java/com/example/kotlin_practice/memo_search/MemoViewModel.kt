package com.example.kotlin_practice.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MemoViewModel : ViewModel() {
    private val _memoList = MutableStateFlow(mutableListOf<Pair<String, Boolean>>())
    val memoList: StateFlow<MutableList<Pair<String, Boolean>>> = _memoList

    fun addMemo(memo: String) {
        _memoList.value = _memoList.value.toMutableList().apply { add(memo to false) }
    }

    fun updateMemo(index: Int, checked: Boolean) {
        _memoList.value = _memoList.value.toMutableList().apply {
            this[index] = this[index].copy(second = checked)
        }
    }

    fun deleteChecked() {
        _memoList.value = _memoList.value.filter { !it.second }.toMutableList()
    }
}
