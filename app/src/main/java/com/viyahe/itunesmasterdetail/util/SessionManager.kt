package com.viyahe.itunesmasterdetail.util

import android.content.Context
import android.content.SharedPreferences
import com.securepreferences.SecurePreferences

object SessionManager {
    private const val KEY_LAST_VISITED_ID             = "UserSessionManager.KEY_LAST_VISITED_ID"
    private const val KEY_LAST_VISITED_WRAPPER_TYPE   = "UserSessionManager.KEY_LAST_VISITED_WRAPPER_TYPE"
    private const val KEY_LAST_DATE_VISITED           = "UserSessionManager.KEY_LAST_DATE_VISITED"

    private lateinit var sharedPref: SecurePreferences

    fun init(context: Context) {
        sharedPref = SecurePreferences(context)
    }

    var lastVisitedId: String
        get() = sharedPref.getString(KEY_LAST_VISITED_ID, "") ?: ""
        set(value) {
            sharedPref.edit { it.putString(KEY_LAST_VISITED_ID, value) }
        }

    var lastVisitedWrapperType: String
        get() = sharedPref.getString(KEY_LAST_VISITED_WRAPPER_TYPE, "") ?: ""
        set(value) {
            sharedPref.edit { it.putString(KEY_LAST_VISITED_WRAPPER_TYPE, value) }
        }

    var lastDateVisited: String
        get() = sharedPref.getString(KEY_LAST_DATE_VISITED, "") ?: ""
        set(value) {
            sharedPref.edit { it.putString(KEY_LAST_DATE_VISITED, value) }
        }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun clearData() {
        sharedPref.edit().clear()
    }

}