package com.lawrence.util

import java.util.UUID

object AccountUtil {
    fun generateAccountNum(): String {
        return UUID.randomUUID().toString();
    }
}