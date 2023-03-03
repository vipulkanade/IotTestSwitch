package com.vipulkanade.myapplication.repository

import com.vipulkanade.myapplication.ui.data.AppSwitchState
import com.vipulkanade.myapplication.ui.data.PhysicalSwitchState

interface SwitchRepositoryLocal {
    // Local VM Repository
    suspend fun getAppSwitchState(): AppSwitchState
    suspend fun getPhysicalSwitchState(): PhysicalSwitchState
    suspend fun updateAppSwitchStatus(isSwitchOn: Boolean)
    suspend fun updatePhysicalSwitchStatus(isSwitchOn: Boolean)
}