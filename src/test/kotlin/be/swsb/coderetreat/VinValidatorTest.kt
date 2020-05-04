package be.swsb.coderetreat

import be.swsb.coderetreat.VinValidator.validate
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*
import java.util.function.Consumer


internal class VinValidatorTest {
    @Test
    fun givenAListOfValidVins_WhenTheCheckIsDone_ThenAnErrorShouldNOTBeThrown() {
        VALID_VINS.forEach { vin: String? -> assertThat(validate(vin)).isEmpty() }
    }

    @Test
    fun spacesAndDashesAreCleanedFromVins() {
        val vinWithSpaces = "TRUW T28N01 104 6197"
        val vinWithDashes = "2G1-WD57C49-1198247"
        assertThat(validate(vinWithSpaces)).isEmpty
        assertThat(validate(vinWithDashes)).isEmpty
    }

    @Test
    fun lowerCaseAreTransformedToUpperCase() {
        val vinWithLowerCaseCharacters = "truwT28N011046197"
        assertThat(validate(vinWithLowerCaseCharacters)).isEmpty
    }

    @Test
    fun givenAListOfInValidVinsWithout17Char_WhenTheCheckIsDOne_ThenAnErrorShouldBeThrown() {
        INVALID_VINS_NOT17_CHAR.forEach { vin: String? ->
                    assertThat(validate(vin))
                            .hasValueSatisfying { validationError -> assertThat(validationError.errorMessage).contains("VIN_MAX_LENGTH") }
                }
    }

    @Test
    fun givenAListOfInValidVinsWithWrongChecksum_WhenTheCheckIsDOne_ThenAnErrorShouldBeThrown() {
        INVALID_VINS_CHECKSUM_WRONG.forEach { vin: String? ->
                    assertThat(validate(vin))
                            .hasValueSatisfying { validationError -> assertThat(validationError.errorMessage).contains("VIN_ILLEGAL") }
                }
    }

    @Test
    fun givenAListOfInValidVinsThatAreEmpty_WhenTheCheckIsDOne_ThenAnErrorShouldBeThrown() {
        INVALID_VINS_EMPTY_VIN.forEach { vin: String? ->
                    assertThat(validate(vin))
                            .hasValueSatisfying { validationError -> assertThat(validationError.errorMessage).contains("VIN_MANDATORY") }
                }
    }

    @Test
    fun givenAListOfInValidVinsWithoutIlligalChar_WhenTheCheckIsDOne_ThenAnErrorShouldBeThrown() {
        INVALID_VINS_ILLEGAL_CHARS.forEach { vin: String? ->
                    assertThat(validate(vin))
                            .hasValueSatisfying { validationError -> assertThat(validationError.errorMessage).contains("VIN_ILLEGAL_CHARACTER") }
                }
    }

    @Test
    fun givenASpecialValue_WhenTheCheckIsDOne_ThenAnErrorShouldBeThrown() {
        assertThat(validate("ččččččččččččččččč"))
                .hasValueSatisfying { s: ValidationError -> assertThat(s.errorMessage).contains("VIN_ILLEGAL_CHARACTER") }
    }

    companion object {
        val VALID_VINS = listOf(
                "TRUWT28N011046197",
                "2G1WD57C491198247",
                "JH4DA3350GS005185",
                "5TFUM5F18AX006026",
                "1G1JC524417418958",
                "JH4DB8580RS000024",
                "1FTSX21P05EC23578",
                "1M8GDM9AXKP042788"
        )
        val INVALID_VINS_CHECKSUM_WRONG = listOf(
                "TRUWT28N611046197",
                "1M8GDM9AZKP042788"
        )
        val INVALID_VINS_NOT17_CHAR = listOf(
                "sagas",
                "adsrvdsv",
                "12345",
                "1FTSX"
        )
        val INVALID_VINS_EMPTY_VIN = listOf(
                null,
                "",
                "   ",
                "         "
        )
        val INVALID_VINS_ILLEGAL_CHARS = listOf(
                "IIIIIIIIIIIIIIIII",
                "OOOOOOOOOOOOOOOOO",
                "QQQQQQQQQQQQQQQQQ"
        )
    }
}
