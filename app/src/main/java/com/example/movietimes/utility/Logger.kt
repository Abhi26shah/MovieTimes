package com.example.movietimes.utility

import android.util.Log
import com.example.movietimes.BuildConfig

class Logger {

    companion object {

        private val LOG_DISABLED = !BuildConfig.LOGGING_ENABLED && !BuildConfig.DEBUG

        fun info(tag: String, message: String) {
            if (!LOG_DISABLED) {
                Log.i(tag,message)
            }
        }

        fun warn(tag: String, message: String) {
            if (!LOG_DISABLED) {
                Log.w(tag,message)
            }
        }

        fun debug(tag: String, message: String) {
            if (!LOG_DISABLED) {
                Log.d(tag,message)
            }
        }

        fun error(tag: String, message: String) {
            if (!LOG_DISABLED) {
                Log.e(tag,message)
            }
        }

        fun isLoggingEnabled(): Boolean {
            return !LOG_DISABLED
        }

    }
}