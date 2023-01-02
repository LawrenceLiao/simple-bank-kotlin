package com.lawrence.model

import com.lawrence.constant.Currency
import java.math.BigDecimal

data class Balance(val customerName: String, val currency: Currency,
                   val customerBalance: BigDecimal, val bankBalance: BigDecimal)
