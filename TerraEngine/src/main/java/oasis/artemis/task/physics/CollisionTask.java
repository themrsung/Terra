package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.event.physics.CollisionEvent;
import oasis.artemis.object.TObject;
import oasis.artemis.task.Task;
import oasis.artemis.util.ObjectPair;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>CollisionTask</h2>
 * <p>
 * Handles collision between objects.
 * </p>
 */
public final class CollisionTask implements Task {
    @Override
    public void execute(@Nonnull Duration delta) {
        TerraEngine.getState().getWorlds().forEach(world -> {
            // Loop through every object which obeys physics
            world.getObjects().filter(TObject::obeysPhysics).forEach(o1 -> {
                // Loop thorough every other object which obeys physics
                world.getObjects().filter(TObject::obeysPhysics).filter(o -> !o.equals(o1)).forEach(o2 -> {
                    final ObjectPair pair = new ObjectPair(o1, o2);
                    // If objects overlap
                    if (o1.overlaps(o2)) {
                        if (!collisions.contains(pair)) {
                            // Call collision event
                            TerraEngine.getEventManager().callEvent(new CollisionEvent(o1, o2));
                            collisions.add(pair);
                        }
                    } else {
                        collisions.remove(pair);
                    }
                });
            });
        });
    }

    @Nonnull
    private final TList<ObjectPair> collisions = new TArray<>();

    @Nonnull
    @Override
    public Duration getDelay() {
        return Duration.ZERO;
    }

    @Nonnull
    @Override
    public Duration getInterval() {
        return Duration.ZERO;
    }
}
