package oasis.artemis.util;

import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>Tickable</h2>
 * <p>
 * A superinterface for classes that require to be called every tick.
 * </p>
 */
public interface Tickable {
    /**
     * Called every tick.
     *
     * @param delta Actual delta between the last tick and this one
     */
    void tick(@Nonnull Duration delta);

    /**
     * Gets the priority of this tickable.
     *
     * @return {@link Priority}
     */
    @Nonnull
    default Priority getPriority() {return Priority.NORMAL;}

    /**
     * The priority of a tickable.
     */
    enum Priority {
        EARLY, // Called first
        LOWEST,
        LOWER,
        LOW,
        NORMAL,
        HIGH,
        HIGHER,
        MONITOR,
        POST_MONITOR // Called lastly
    }
}
