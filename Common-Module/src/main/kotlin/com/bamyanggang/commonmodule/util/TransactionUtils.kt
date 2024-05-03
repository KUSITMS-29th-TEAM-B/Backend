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

        fun <T> read(func: () -> T): T {
            return transactionHandler.read { func() }
        }

        fun <T> write(func: () -> T): T {
            return transactionHandler.write { func() }
        }

    }

    @Component
    class TransactionHandler{

        @Transactional(readOnly = true)
        fun <T> read(func: () -> T): T {
            return func()
        }

        @Transactional
        fun <T> write(func: () -> T): T {
            return func()
        }
    }

}
