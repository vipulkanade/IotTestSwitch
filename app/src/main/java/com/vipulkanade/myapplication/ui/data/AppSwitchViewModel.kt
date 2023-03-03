package com.vipulkanade.myapplication.ui.data

import androidx.lifecycle.ViewModel
import com.vipulkanade.myapplication.usecase.UpdateAppSwitchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppSwitchViewModel @Inject constructor(private val updateAppSwitchUseCase: UpdateAppSwitchUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(AppSwitchState())
    val uiState: StateFlow<AppSwitchState> = _uiState.asStateFlow()

    fun updateSwitchStatus(isSwitchOn: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isSwitchOn = isSwitchOn
            )
        }
        updateAppSwitchUseCase.execute(isSwitchOn)
    }
}