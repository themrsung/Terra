package oasis.artemis.exception.economy;

import oasis.artemis.exception.TerraException;

import javax.annotation.Nonnull;

/**
 * <h2>EconomyException</h2>
 * <p>An exception thrown by economic causes.</p>
 */
public class EconomyException extends TerraException {
    /**
     * Creates a new EconomyException.
     */
    public EconomyException() {
    }

    /**
     * Creates a new EconomyException.
     */
    public EconomyException(@Nonnull String message) {
        super(message);
    }

    /**
     * Creates a new EconomyException.
     */
    public EconomyException(@Nonnull String message, @Nonnull Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new EconomyException.
     */
    public EconomyException(@Nonnull Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new EconomyException.
     */
    public EconomyException(@Nonnull String message, @Nonnull Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
