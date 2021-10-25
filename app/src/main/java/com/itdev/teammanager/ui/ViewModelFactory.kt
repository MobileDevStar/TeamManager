package com.itdev.teammanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itdev.teammanager.persistence.UserDao
import java.lang.IllegalArgumentException

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val dataSource: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource) as T
        }

        throw IllegalArgumentException("Unknown view Model Class")
    }
}