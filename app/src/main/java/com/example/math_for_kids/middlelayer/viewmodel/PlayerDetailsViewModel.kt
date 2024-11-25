package com.example.math_for_kids.middlelayer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.math_for_kids.databaselayer.instance.RetrofitInstance
import com.example.math_for_kids.databaselayer.api.nonsql.UpdateLevel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PlayerDetailsViewModel : ViewModel() {
    var responseBody by mutableStateOf<Map<String, Any>>(emptyMap())
    private var errorMessage by mutableStateOf("")
    private var code by mutableIntStateOf(0)
    private var isSuccess by mutableStateOf(false)

    fun getPlayer(playerId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.nonSqlApiServices.getPlayer(playerId)
                isSuccess = response.isSuccessful
                responseBody = response.body() ?: emptyMap()
                code = response.code()
            } catch (e: IOException) {
                isSuccess = false
                errorMessage = "Network Error: ${e.message}"
            } catch (e: HttpException) {
                isSuccess = false
                errorMessage = "HTTP Error: ${e.message}"
            } catch (e: Exception) {
                isSuccess = false
                errorMessage = "Unknown Server Error: ${e.message}"
            }
        }
    }

    fun updateLevel(playerId: String, newLevel: String) {
        val requestBody = UpdateLevel(level = newLevel)
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.nonSqlApiServices.updateLevel(playerId, requestBody)
                isSuccess = response.isSuccessful
                responseBody = response.body() ?: emptyMap()
                code = response.code()
            } catch (e: IOException) {
                isSuccess = false
                errorMessage = "Network Error: ${e.message}"
            } catch (e: HttpException) {
                isSuccess = false
                errorMessage = "HTTP Error: ${e.message}"
            } catch (e: Exception) {
                isSuccess = false
                errorMessage = "Unknown Server Error: ${e.message}"
            }
        }
    }
}