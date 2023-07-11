package oasis.artemis.listener;

import oasis.artemis.event.Event;

import javax.annotation.Nonnull;

/**
 * <h2>Listener</h2>
 * <p>
 * Subscribes to and handles the processing of an {@link Event}.
 * </p>
 *
 * @param <E> Type of event this listener handles
 */
public interface Listener<E extends Event> {
    /**
     * Called when the event is called.
     * Implement your logic here.
     *
     * @param event Event that was called
     */
    void handle(@Nonnull E event);

    /**
     * Gets the class of event handled by this listener.
     *
     * @return Class of {@link E}
     */
    @Nonnull
    Class<E> getEventClass();

    /**
     * Gets the priority of this listener.
     *
     * @return {@link Priority}
     */
    @Nonnull
    default Priority getPriority() {return Priority.NORMAL;}

    /**
     * The priority of an event listener.
     * Lower priorities get called first.
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

    //
    // DO NOT OVERRIDE
    //

    /**
     * Called by the event manager to initiate the handling of an event.
     * Do not override.
     *
     * @param event Raw uncast event
     */
    default void handleRaw(@Nonnull Event event) {
        if (getEventClass().isInstance(event)) handle(getEventClass().cast(event));
    }
}
