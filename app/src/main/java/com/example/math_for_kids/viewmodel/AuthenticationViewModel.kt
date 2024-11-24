package com.example.math_for_kids.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.math_for_kids.database.api.sql.LoginCreds
import com.example.math_for_kids.database.api.sql.RegisterCreds
import com.example.math_for_kids.database.instance.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AuthenticationViewModel : ViewModel() {
    var responseBody by mutableStateOf<Map<String, Any>>(emptyMap())
    var errorMessage by mutableStateOf("")
    private var code by mutableIntStateOf(0)
    var isSuccess by mutableStateOf(false)

    fun login(email: String, password: String) {
        val requestBody = LoginCreds(email, password)
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.sqlApiServices.login(requestBody)
                isSuccess = response.isSuccessful
                code = response.code()
                if (isSuccess) {
                    responseBody = response.body() ?: emptyMap()
                } else {
                    errorMessage = response.errorBody()?.string() ?: "{}"
                }
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

    fun register(email: String, password: String, name: String) {
        val requestBody = RegisterCreds(email, password, name)
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.sqlApiServices.register(requestBody)
                isSuccess = response.isSuccessful
                code = response.code()
                if (isSuccess) {
                    responseBody = response.body() ?: emptyMap()
                } else {
                    errorMessage = response.errorBody()?.string() ?: "{}"
                }
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