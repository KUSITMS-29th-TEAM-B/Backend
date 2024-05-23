package com.bamyanggang.lock

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class LockManagerTest {

    @Test
    fun testLockByKey() {
        // Given
        val key = "key"
        val body = {
            Thread.sleep(10)
            "body"
        }

        // When
        val result = LockManager.lockByKey(key, body = body)

        // Then
        assert(result == "body")
    }


    @Test
    fun testLockByKeyTimeout() {
        // Given
        val key = "key"
        val time = 1L
        val body = {
            Thread.sleep(2000)
            "body"
        }

        val countDownLatch = CountDownLatch(2)
        var isTimeout = false
        // When
        for (i in 0..1) {

            Thread {
                try {
                    LockManager.lockByKey(key, time, body)
                } catch (e: RuntimeException) {
                    isTimeout = true
                } finally {
                    countDownLatch.countDown()
                }
            }.start()
        }
        countDownLatch.await()

        // Then
        isTimeout shouldBe true
    }

    @Test
    fun testLockConcurrent() {
        // Given
        var count = 0
        val key = "key"
        val body = {
            val currentCount = count
            Thread.sleep(1)
            count = currentCount + 1
        }

        val countDown = 100

        val countDownLatch = CountDownLatch(countDown)

        // When
        for (i in 0 until countDown) {
            Thread {
                LockManager.lockByKey(key, body = body)
                countDownLatch.countDown()
            }.start()
        }

        countDownLatch.await()

        // Then
        count shouldBe 100
    }

    @Test
    fun testLockIndependence() {
        // Given
        val key1 = "key1"
        val key2 = "key2"
        val body = { Thread.sleep(1000) }
        val countDownLatch = CountDownLatch(2)

        // When
        Thread {
            LockManager.lockByKey(key1, body = body)
            countDownLatch.countDown()
        }.start()

        Thread {
            LockManager.lockByKey(key2, body = body)
            countDownLatch.countDown()
        }.start()

        // Then
        countDownLatch.await(2, TimeUnit.SECONDS) shouldBe true
    }

    @Test
    fun testLockWait() {
        // Given
        val key = "key"
        val body = { Thread.sleep(1000) }
        val countDownLatch = CountDownLatch(2)

        // When
        Thread {
            LockManager.lockByKey(key, body = body)
            countDownLatch.countDown()
        }.start()

        Thread {
            LockManager.lockByKey(key, body = body)
            countDownLatch.countDown()
        }.start()

        // Then
        countDownLatch.await(3, TimeUnit.SECONDS) shouldBe true
    }

}
