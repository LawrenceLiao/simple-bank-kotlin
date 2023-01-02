package com.lawrence.model

import com.lawrence.constant.Currency
import com.lawrence.constant.Currency.*
import com.lawrence.constant.TransactionType.*
import com.lawrence.exception.InsufficientBalanceException
import com.lawrence.util.DateTimeUtil
import java.math.BigDecimal


class Account(val id: String,
              val customerName: String,
              val currency: Currency = AUD,
              private val transactions: MutableList<Transaction> = mutableListOf()) {

    fun deposit(amount: BigDecimal): BigDecimal {
        transactions.add(Transaction(id, amount, CREDIT, DateTimeUtil.getCurrentUTCDateTime()))
        return balance();
    }

    fun withdraw(amount: BigDecimal): BigDecimal {
        if (balance() < amount) {
            throw InsufficientBalanceException("No money");
        }
        transactions.add(Transaction(id, amount, DEBIT, DateTimeUtil.getCurrentUTCDateTime()))
        return balance();
    }

    fun balance(): BigDecimal {
        return transactions.map { transaction -> if (transaction.type == CREDIT) transaction.amount else transaction.amount.negate()}
            .reduce { acc, curr -> acc.add(curr) }
    }

}