package oasis.artemis.event;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.listener.Listener;

import javax.annotation.Nonnull;
import java.util.Comparator;

/**
 * <h2>EventManager</h2>
 * <p>
 * Handles the registration of event listeners,
 * and the calling of events.
 * </p>
 * <p>
 * You can extend this class to implement asynchronous processing,
 * which is not possible by default.
 * </p>
 */
public class EventManager {
    /**
     * Calls an event to be handled.
     *
     * @param event Event to call
     */
    public void callEvent(@Nonnull Event event) {
        listeners.forEach(l -> l.handleRaw(event));

        final Runnable onHandled = event.onHandled();
        if (onHandled != null) onHandled.run();
    }

    /**
     * Registers a listener.
     *
     * @param listener Listener to register
     */
    public void registerListener(@Nonnull Listener<?> listener) {
        listeners.add(listener);
        listeners.sort(Comparator.comparing(Listener::getPriority));
    }

    /**
     * Unregisters a listener.
     *
     * @param listener Listener to unregister
     */
    public void unregisterListener(@Nonnull Listener<?> listener) {
        listeners.remove(listener);
    }

    @Nonnull
    private final TList<Listener<?>> listeners;

    public EventManager() {
        this.listeners = new TArray<>();
    }
}
