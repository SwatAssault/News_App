package com.rkhasanov.newsApp.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rkhasanov.newsApp.model.database.room.DatabaseRepo
import com.rkhasanov.newsApp.model.database.room.MainDatabase
import com.rkhasanov.newsApp.utils.DATABASE


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    init {
        val roomDAO = MainDatabase.getInstance(application).getRoomDAO()
        DATABASE = DatabaseRepo(roomDAO)
    }

}