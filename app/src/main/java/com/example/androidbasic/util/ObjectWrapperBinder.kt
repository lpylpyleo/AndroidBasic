package com.example.androidbasic.util

import android.os.Binder

data class ObjectWrapperBinder(val obj: Any) : Binder()