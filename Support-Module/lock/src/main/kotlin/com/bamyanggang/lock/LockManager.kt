package com.bamyanggang.lock

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

class LockManager {

    companion object {
        private val lockMap = ConcurrentHashMap<String, ReentrantLock>()
        private val lockCountMap = ConcurrentHashMap<String, AtomicInteger>()

        fun <R> lockByKey(key: String, time: Long = 10, body: () -> R): R {
            val lock = lockMap.getOrPut(key) { ReentrantLock() }
            val lockCount = lockCountMap.getOrPut(key) { AtomicInteger(0) }
            lockCount.incrementAndGet()
            if (lock.tryLock(time, TimeUnit.SECONDS)) {
                try {
                    return body()
                }finally {
                    lock.unlock()
                    if(lockCount.decrementAndGet() == 0) {
                        lockMap.remove(key)
                        lockCountMap.remove(key)
                    }
                }
            } else {
                throw RuntimeException("Lock timeout")
            }
        }
    }

}
