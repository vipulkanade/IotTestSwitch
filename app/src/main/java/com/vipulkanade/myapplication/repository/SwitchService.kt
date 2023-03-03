package com.vipulkanade.myapplication.repository

import com.vipulkanade.myapplication.ui.data.SwitchState
import com.vipulkanade.myapplication.ui.data.SwitchUpdate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface SwitchService {
    @GET("switches")
    suspend fun getSwitches(): SwitchState

    @PUT("switches")
    suspend fun updateSwitches(@Body switchUpdate: SwitchUpdate): SwitchState
}