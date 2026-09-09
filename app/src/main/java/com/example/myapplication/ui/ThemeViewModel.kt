package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.ThemeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ThemeViewModel(private val repo: ThemeRepository) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> = repo.isDarkTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun onThemeChanged(enabled: Boolean) {
        viewModelScope.launch { repo.setDarkTheme(enabled) }
    }
}
