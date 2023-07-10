package oasis.artemis.exception.physics;

import oasis.artemis.exception.TerraException;

import javax.annotation.Nonnull;

/**
 * <h2>PhysicsException</h2>
 * <p>The base class for all physics exceptions.</p>
 */
public class PhysicsException extends TerraException {
    /**
     * Creates a new physics exception.
     */
    public PhysicsException() {
    }

    /**
     * Creates a new physics exception.
     */
    public PhysicsException(@Nonnull String message) {
        super(message);
    }

    /**
     * Creates a new physics exception.
     */
    public PhysicsException(@Nonnull String message, @Nonnull Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new physics exception.
     */
    public PhysicsException(@Nonnull Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new physics exception.
     */
    public PhysicsException(@Nonnull String message, @Nonnull Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
