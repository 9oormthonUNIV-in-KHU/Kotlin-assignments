package com.example.myapplication.navigate

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow //실시간 상태변화 파악 가능

class ListViewModel : ViewModel() { //listviewmodel은 viewmodel을 상속한다는 뜻 -> 데이터 유지 위해 필요
    private val _todoMap = mutableStateMapOf<String, MutableStateFlow<List<Pair<Boolean, String>>>>()
    //투두리스트 저장: key-part이름, value-해당 파트의 MutableStateFlow<List<Pair<Boolean, String>>>
    val partList = listOf("DevRel", "FRONTEND", "BACKEND", "AI", "MOBILE")

    fun getListFlow(part: String): StateFlow<List<Pair<Boolean, String>>> {
        return _todoMap.getOrPut(part) { MutableStateFlow(emptyList()) } //getOrPut(key) { defaultValue }: key가 없으면 생성해서 넣고 반환, 있으면 있는 걸 반환
    }

    fun addToList(part: String, text: String) {
        val current = getListFlow(part).value.toMutableList()
        _todoMap[part]?.value = current + (false to text) //?.value: 내부 실제 값에 접근
    }

    fun toggleCheck(part: String, index: Int) {
        val current = getListFlow(part).value.toMutableList()
        val (checked, text) = current[index]
        current[index] = (!checked to text)
        _todoMap[part]?.value = current
    }

    fun deleteChecked(part: String) {
        val current = getListFlow(part).value.filterNot { it.first }
        _todoMap[part]?.value = current
    }
}