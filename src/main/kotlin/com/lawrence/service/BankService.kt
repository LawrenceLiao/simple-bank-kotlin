package com.lawrence.service

import com.lawrence.constant.Currency
import com.lawrence.model.Balance
import java.math.BigDecimal

class BankService(private val customerService: CustomerService) {
    fun deposit(customerName: String, amount: BigDecimal, currency: Currency): Balance {
        val customerBalance = customerService.deposit(customerName, amount, currency)
        val bankBalance = customerService.totalBalance(currency)
        return Balance(customerName, currency, customerBalance, bankBalance);
    }

    fun withdraw(customerName: String, amount: BigDecimal, currency: Currency): Balance {
        val customerBalance = customerService.withdraw(customerName, amount, currency)
        val bankBalance = customerService.totalBalance(currency)
        return Balance(customerName, currency, customerBalance, bankBalance)
    }
}