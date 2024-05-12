package com.bamyanggang.commonmodule.util

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TransactionUtils(
    _transactionHandler: TransactionHandler
) {

    init {
        transactionHandler = _transactionHandler
    }

    companion object{
        private lateinit var transactionHandler: TransactionHandler

        fun <T> readable(func: () -> T): T {
            return transactionHandler.readable { func() }
        }

        fun <T> writable(func: () -> T): T {
            return transactionHandler.writable { func() }
        }
    }

    @Component
    class TransactionHandler{

        @Transactional(readOnly = true)
        fun <T> readable(func: () -> T): T {
            return func()
        }

        @Transactional
        fun <T> writable(func: () -> T): T {
            return func()
        }
    }

}
