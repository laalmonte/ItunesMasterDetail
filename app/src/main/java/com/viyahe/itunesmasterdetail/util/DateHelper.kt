package com.viyahe.itunesmasterdetail.util

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    public fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}