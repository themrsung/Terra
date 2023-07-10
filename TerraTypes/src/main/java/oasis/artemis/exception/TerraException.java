package oasis.artemis.exception;

import javax.annotation.Nonnull;

/**
 * <h2>TerraException</h2>
 * <p>The base class for all Terra exceptions.</p>
 */
public class TerraException extends Exception {
    /**
     * Creates a new TerraException.
     */
    public TerraException() {
    }

    /**
     * Creates a new TerraException.
     */
    public TerraException(@Nonnull String message) {
        super(message);
    }

    /**
     * Creates a new TerraException.
     */
    public TerraException(@Nonnull String message, @Nonnull Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new TerraException.
     */
    public TerraException(@Nonnull Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new TerraException.
     */
    public TerraException(@Nonnull String message, @Nonnull Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
