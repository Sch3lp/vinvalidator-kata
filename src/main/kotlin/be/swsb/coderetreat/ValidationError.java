package be.swsb.coderetreat;

import java.util.Objects;

public class ValidationError {
    private final String errorMessage;

    private ValidationError(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ValidationError from(final String error) {
        return new ValidationError(error);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ValidationError that = (ValidationError) o;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage);
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "error='" + errorMessage + '\'' +
                '}';
    }
}
