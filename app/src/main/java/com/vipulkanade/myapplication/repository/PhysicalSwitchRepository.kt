package com.vipulkanade.myapplication.repository

import com.vipulkanade.myapplication.ui.data.AppSwitchState
import com.vipulkanade.myapplication.ui.data.PhysicalSwitchState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhysicalSwitchRepository @Inject constructor() : SwitchRepositoryLocal {
    private val _uiState = MutableStateFlow(PhysicalSwitchState())
    override suspend fun getAppSwitchState(): AppSwitchState {
        TODO("Not yet implemented")
    }

    override suspend fun getPhysicalSwitchState(): PhysicalSwitchState = _uiState.value

    override suspend fun updateAppSwitchStatus(isSwitchOn: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhysicalSwitchStatus(isSwitchOn: Boolean) {
        _uiState.emit(_uiState.value.copy(isSwitchOn = isSwitchOn))
    }
}