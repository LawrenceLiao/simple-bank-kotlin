package com.lawrence.util

import java.time.OffsetDateTime
import java.time.ZoneOffset.*

object DateTimeUtil {
    fun getCurrentUTCDateTime(): OffsetDateTime {
        return OffsetDateTime.now(UTC);
    }
}