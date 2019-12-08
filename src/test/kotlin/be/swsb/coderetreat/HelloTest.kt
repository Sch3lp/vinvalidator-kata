package be.swsb.coderetreat

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


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
        val helloMock = mockk<HelloService>()
        every { helloMock.sayGreeting() } returns "Snarf!"
        assertThat(helloMock.sayGreeting()).isEqualTo("Snarf!")
    }
}
