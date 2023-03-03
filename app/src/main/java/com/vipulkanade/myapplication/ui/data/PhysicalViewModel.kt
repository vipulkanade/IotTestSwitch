package com.vipulkanade.myapplication.ui.data

import androidx.lifecycle.ViewModel
import com.vipulkanade.myapplication.usecase.UpdatePhysicalSwitchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PhysicalViewModel @Inject constructor(private val updatePhysicalSwitchUseCase: UpdatePhysicalSwitchUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(PhysicalSwitchState())
    val uiState: StateFlow<PhysicalSwitchState> = _uiState.asStateFlow()

    fun updateSwitchStatus(isSwitchOn: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isSwitchOn = isSwitchOn
            )
        }
        updatePhysicalSwitchUseCase.execute(isSwitchOn)
    }
}