package com.lawrence.service

import com.lawrence.constant.Currency
import com.lawrence.exception.CustomerNotFoundException
import com.lawrence.model.Customer
import java.math.BigDecimal

class CustomerService(private val customers: MutableMap<String, Customer> = mutableMapOf()) {

    fun deposit(customerName: String, amount: BigDecimal, currency: Currency): BigDecimal {
        customers.computeIfAbsent(customerName, ::createCustomer)
        val customer = getCustomer(customerName)!!
        return customer.deposit(amount, currency)
    }

    fun withdraw(customerName: String, amount: BigDecimal, currency: Currency): BigDecimal {
        val customer = getCustomer(customerName) ?: throw CustomerNotFoundException("No customer")
        return customer.withdraw(amount, currency)
    }


    fun totalBalance(currency: Currency): BigDecimal {
        return customers.values.map { it.balance(currency) }.reduce { acc, balance -> acc + balance }
    }

    private fun getCustomer(customerName: String): Customer? {
        return customers[customerName]
    }

    private fun createCustomer(customerName: String): Customer {
        val customer = Customer(customerName)
        customers[customerName] = customer
        return customer
    }
}