package com.itdev.teammanager

import android.content.Context
import com.itdev.teammanager.persistence.UserDao
import com.itdev.teammanager.persistence.UsersDatabase
import com.itdev.teammanager.ui.ViewModelFactory

object Injection {

    fun provideuserDataSource(context: Context): UserDao {
        val databse = UsersDatabase.getInstance(context)
        return databse.userDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideuserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}