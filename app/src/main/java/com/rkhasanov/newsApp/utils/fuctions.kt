package com.rkhasanov.newsApp.utils

import android.view.Gravity
import android.widget.Toast

fun toastPopUp(message: String) {
    val toast = Toast.makeText(APP_CONTEXT, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
    toast.show()
}