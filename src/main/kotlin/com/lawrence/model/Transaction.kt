package com.lawrence.model

import com.lawrence.constant.TransactionType
import java.math.BigDecimal
import java.time.OffsetDateTime

data class Transaction(val accountId: String, val amount: BigDecimal, val type: TransactionType, val createdDateTime: OffsetDateTime)
