package com.vipulkanade.myapplication.repository

import com.vipulkanade.myapplication.ui.data.SwitchState
import com.vipulkanade.myapplication.util.Resource

interface SwitchRepository {
    suspend fun getSwitches(): Resource<SwitchState>
    suspend fun setFirstSwitch(checked: Boolean): Resource<SwitchState>
    suspend fun setSecondSwitch(checked: Boolean): Resource<SwitchState>
}