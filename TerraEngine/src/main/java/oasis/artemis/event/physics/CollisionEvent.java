package oasis.artemis.event.physics;

import oasis.artemis.event.Cancellable;
import oasis.artemis.event.Event;
import oasis.artemis.object.TObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <h2>CollisionEvent</h2>
 * <p>Called when two objects which obey physics collide.</p>
 */
public final class CollisionEvent extends Event implements Cancellable {
    /**
     * Creates a new collision event.
     *
     * @param object1 Object 1
     * @param object2 Object 2
     */
    public CollisionEvent(@Nonnull TObject object1, @Nonnull TObject object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    /**
     * Creates a new collision event.
     *
     * @param object1   Object 1
     * @param object2   Object 2
     * @param cause     Cause of the event
     * @param onHandled Runnable to execute post-handling
     */
    public CollisionEvent(@Nonnull TObject object1, @Nonnull TObject object2, @Nullable Event cause, @Nullable Runnable onHandled) {
        super(cause, onHandled);
        this.object1 = object1;
        this.object2 = object2;
    }

    /**
     * Gets the first object of this collision.
     *
     * @return Object 1
     */
    @Nonnull
    public TObject getObject1() {
        return object1;
    }

    /**
     * Gets the second object of this collision.
     *
     * @return Object 2
     */
    @Nonnull
    public TObject getObject2() {
        return object2;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Nonnull
    private final TObject object1;
    @Nonnull
    private final TObject object2;
    private boolean cancelled;
}
