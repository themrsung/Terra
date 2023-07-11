package oasis.artemis.event;

/**
 * <h2>Cancellable</h2>
 * <p>
 * This interface denotes that an event can be cancelled.
 * Cancelling the event in a lower-priority listener will result in the event
 * not being processed by all succeeding listeners.
 * </p>
 * <p>
 * In order for this mechanism to work, the event listeners must check if
 * {@link Cancellable#isCancelled()} is false before processing.
 * If this is ignored, the event can be processed regardless of it being cancelled.
 * </p>
 */
public interface Cancellable {
    /**
     * Returns whether this event has been cancelled by a lower-priority listener.
     *
     * @return {@code true} if this event was cancelled
     */
    boolean isCancelled();

    /**
     * Sets whether this event should be ignored by higher-priority listeners.
     * Certain listeners will ignore this and process it regardless.
     *
     * @param cancelled {@code true} to cancel this event
     */
    void setCancelled(boolean cancelled);
}
