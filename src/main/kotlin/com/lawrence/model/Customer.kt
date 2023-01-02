package com.lawrence.model

import com.lawrence.constant.Currency
import com.lawrence.exception.InsufficientBalanceException
import com.lawrence.util.AccountUtil
import java.math.BigDecimal

class Customer(val name: String, val accounts: MutableList<Account> = mutableListOf()) {

    fun deposit(amount: BigDecimal, currency: Currency): BigDecimal {
        val account = getAccount(currency) ?: createAccount(currency)
        return account.deposit(amount)
    }

    fun withdraw(amount: BigDecimal, currency: Currency): BigDecimal {
        val account = getAccount(currency) ?: throw InsufficientBalanceException("No money")
        return account.withdraw(amount);
    }

    fun balance(currency: Currency): BigDecimal {
        return accounts.filter { it.currency == currency }.map { it.balance() }.reduce { acc, balance -> acc + balance }
    }

    private fun getAccount(currency: Currency): Account? {
        return accounts.find { it.currency == currency };
    }

    private fun createAccount(currency: Currency): Account {
        val account = Account(AccountUtil.generateAccountNum(), name, currency)
        accounts.add(account);
        return account;
    }
}