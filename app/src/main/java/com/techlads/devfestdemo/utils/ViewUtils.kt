package com.techlads.devfestdemo.utils

import android.content.Context


fun Context.getWidthInPercent(percentage: Int) = (resources.displayMetrics.widthPixels * percentage) / 100
fun Context.getHeightInPercent(percentage: Int) = (resources.displayMetrics.heightPixels * percentage) / 100