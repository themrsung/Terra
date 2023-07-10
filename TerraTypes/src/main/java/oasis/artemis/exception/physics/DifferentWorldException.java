package oasis.artemis.exception.physics;

import oasis.artemis.world.World;

import javax.annotation.Nonnull;

/**
 * <h2>DifferentWorldException</h2>
 * <p>
 * This exception is thrown when an operation requires the worlds to be equal,
 * but a different world has been provided.
 * </p>
 */
public class DifferentWorldException extends PhysicsException {
    /**
     * Creates a new DifferentWorldException.
     *
     * @param required The required world
     * @param provided The provided world
     */
    public DifferentWorldException(@Nonnull World required, @Nonnull World provided) {
        this.required = required;
        this.provided = provided;
    }

    /**
     * Creates a new DifferentWorldException.
     *
     * @param message  Error message
     * @param required The required world
     * @param provided The provided world
     */
    public DifferentWorldException(@Nonnull String message, @Nonnull World required, @Nonnull World provided) {
        super(message);
        this.required = required;
        this.provided = provided;
    }

    /**
     * Creates a new DifferentWorldException.
     *
     * @param message  Error message
     * @param cause    Cause of this exception
     * @param required The required world
     * @param provided The provided world
     */
    public DifferentWorldException(@Nonnull String message, @Nonnull Throwable cause, @Nonnull World required, @Nonnull World provided) {
        super(message, cause);
        this.required = required;
        this.provided = provided;
    }

    /**
     * Creates a new DifferentWorldException.
     *
     * @param cause    Cause of this exception
     * @param required The required world
     * @param provided The provided world
     */
    public DifferentWorldException(@Nonnull Throwable cause, @Nonnull World required, @Nonnull World provided) {
        super(cause);
        this.required = required;
        this.provided = provided;
    }

    @Nonnull
    private final World required;

    @Nonnull
    private final World provided;

    /**
     * Gets the required world in order for this exception to not occur.
     *
     * @return Required world
     */
    @Nonnull
    public World getRequired() {
        return required;
    }

    /**
     * Gets the provided world which caused the invocation of this exception.
     *
     * @return Provided world
     */
    @Nonnull
    public World getProvided() {
        return provided;
    }
}
