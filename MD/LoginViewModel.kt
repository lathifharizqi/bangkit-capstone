package com.example.feemeowapp.ui.page.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.feemeowapp.ui.model.UserModel
import com.example.feemeowapp.ui.model.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel (private val pref: UserPreference):  ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
    fun login(){
        viewModelScope.launch {
            pref.login()
        }
    }
}