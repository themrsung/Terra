package oasis.artemis.event;

import javax.annotation.Nullable;

/**
 * <h2>Event</h2>
 * <p>
 * An event is called either before or after an action.
 * Events are handled synchronously in the order they were called.
 * </p>
 * <p>
 * Keep in mind when inheriting other events,
 * that the event will be handled once per every parent instance.
 * Seal all events as {@code final} unless inheritance is absolutely necessary.
 * Always declare abstract events {@code abstract}.
 * </p>
 * <p>
 * Creating builder is recommended when there are more than 4-5 required parameters,
 * or the order of the parameters in the constructor is not very obvious.
 * </p>
 */
public abstract class Event {
    /**
     * Default constructor.
     * All parameters will be {@code null}.
     */
    public Event() {
        this.cause = null;
        this.onHandled = null;
    }

    /**
     * Default all-args constructor.
     *
     * @param cause     Cause of this event
     * @param onHandled Runnable to execute post-handling
     */
    public Event(@Nullable Event cause, @Nullable Runnable onHandled) {
        this.cause = cause;
        this.onHandled = onHandled;
    }

    /**
     * Gets the event preceding this event in an event chain.
     * Value {@code null} means this is the first event in a chain,
     * or is an independent singular event.
     *
     * @return Cause
     */
    @Nullable
    public Event getCause() {
        return cause;
    }

    /**
     * Gets the runnable to execute after handling.
     * This is guaranteed to be called only once,
     * after every listener has completed its handling.
     *
     * @return Runnable executed on handled
     */
    @Nullable
    public Runnable onHandled() {
        return onHandled;
    }

    @Nullable
    private final Event cause;

    @Nullable
    private final Runnable onHandled;
}
