package be.swsb.coderetreat

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HelloTest {

    @Test
    fun greet_ShouldReturnWorld() {
        assertThat(Hello().greet()).isEqualTo("World!")
    }
}