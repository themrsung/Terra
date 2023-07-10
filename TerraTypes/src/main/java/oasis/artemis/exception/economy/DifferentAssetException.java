package oasis.artemis.exception.economy;

import oasis.artemis.economy.Asset;

import javax.annotation.Nonnull;

/**
 * <h2>DifferentAssetException</h2>
 * <p>
 * This is thrown when an operation requires asset types to be equal,
 * but the given parameter's asset was not equal to the required type.
 * </p>
 */
public class DifferentAssetException extends EconomyException {
    /**
     * Creates a new DifferentAssetException.
     *
     * @param required The required asset
     * @param provided The provided asset
     */
    public DifferentAssetException(@Nonnull Asset required, @Nonnull Asset provided) {
        this.required = required;
        this.provided = provided;
    }

    /**
     * Creates a new DifferentAssetException.
     *
     * @param message  Error message
     * @param required The required asset
     * @param provided The provided asset
     */
    public DifferentAssetException(@Nonnull String message, @Nonnull Asset required, @Nonnull Asset provided) {
        super(message);
        this.required = required;
        this.provided = provided;
    }

    /**
     * Creates a new DifferentAssetException.
     *
     * @param message  Error message
     * @param cause    Cause of this exception
     * @param required The required asset
     * @param provided The provided asset
     */
    public DifferentAssetException(@Nonnull String message, @Nonnull Throwable cause, @Nonnull Asset required, @Nonnull Asset provided) {
        super(message, cause);
        this.required = required;
        this.provided = provided;
    }

    /**
     * Creates a new DifferentAssetException.
     *
     * @param cause    Cause of this exception
     * @param required The required asset
     * @param provided The provided asset
     */
    public DifferentAssetException(@Nonnull Throwable cause, @Nonnull Asset required, @Nonnull Asset provided) {
        super(cause);
        this.required = required;
        this.provided = provided;
    }

    @Nonnull
    private final Asset required;

    @Nonnull
    private final Asset provided;


    /**
     * Gets the required asset in order for this exception to have not occurred.
     *
     * @return Required asset
     */
    @Nonnull
    public Asset getRequired() {
        return required;
    }

    /**
     * Gets the provided asset which caused the invocation of this exception.
     *
     * @return Provided asset
     */
    @Nonnull
    public Asset getProvided() {
        return provided;
    }
}
