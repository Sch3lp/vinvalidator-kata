package be.swsb.coderetreat

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class HelloTest {

    @Test
    fun greet_ShouldReturnWorld() {
        assertThat(Hello().greet()).isEqualTo("World!")
    }

    @Test
    fun sayGreeting_WithoutMock_ShouldReturnWorld() {
        val helloMock = HelloService()
        assertThat(helloMock.sayGreeting()).isEqualTo("World!")
    }

    @Test
    fun sayGreeting_WithMock_ShouldReturnMockedGreeting() {
        val helloMock = mock<HelloService>()
        whenever(helloMock.sayGreeting()).thenReturn("Snarf!")
        assertThat(helloMock.sayGreeting()).isEqualTo("Snarf!")
    }
}