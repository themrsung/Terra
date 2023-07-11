package oasis.artemis.physics;

/**
 * <h2>Face</h2>
 * <p>
 *     Represents a single-axis direction an object can move in.
 * </p>
 */
public enum Face {
    POSITIVE_X,
    POSITIVE_Y,
    POSITIVE_Z,
    NEGATIVE_X,
    NEGATIVE_Y,
    NEGATIVE_Z;

    /**
     * Checks if this face is horizontal. (is X)
     * @return {@code true} when {@link Face#POSITIVE_X} or {@link Face#NEGATIVE_X}.
     */
    public boolean isX() {
        return switch (this) {
            case POSITIVE_X, NEGATIVE_X -> true;
            default -> false;
        };
    }

    /**
     * Checks if this face is vertical. (is Y)
     * @return {@code true} when {@link Face#POSITIVE_Y} or {@link Face#NEGATIVE_Y}.
     */
    public boolean isY() {
        return switch (this) {
            case POSITIVE_Y, NEGATIVE_Y -> true;
            default -> false;
        };
    }

    /**
     * Checks if this face represents depth. (is Z)
     * @return {@code true} when {@link Face#POSITIVE_Z} or {@link Face#NEGATIVE_Z}.
     */
    public boolean isZ() {
        return switch (this) {
            case POSITIVE_Z, NEGATIVE_Z -> true;
            default -> false;
        };
    }
}
