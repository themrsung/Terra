package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
import oasis.artemis.collection.set.THashSet;
import oasis.artemis.collection.set.TSet;
import oasis.artemis.event.physics.CollisionEvent;
import oasis.artemis.map.THashMap;
import oasis.artemis.map.TMap;
import oasis.artemis.object.TObject;
import oasis.artemis.task.Task;
import oasis.artemis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <h2>CollisionTask</h2>
 * <p>
 *     Handles collision between objects.
 * </p>
 */
public final class CollisionTask implements Task {
    @Override
    public void execute(@Nonnull Duration delta) {
        TerraEngine.getState().getWorlds().forEach(world -> {
            final TMap<TObject, TSet<TObject>> collidingObjects = collisions.getOrDefaultPointer(world, new THashMap<>());

            // Loop through every object which obeys physics
            world.getObjects().filter(TObject::obeysPhysics).forEach(o1 -> {
                // Loop thorough every other object which obeys physics
                world.getObjects().filter(TObject::obeysPhysics).filter(o -> !o.equals(o1)).forEach(o2 -> {
                    // Initialize collisions list
                    final TSet<TObject> collisions = collidingObjects.getOrDefaultPointer(o1, new THashSet<>());

                    // If objects overlap and have not already collided
                    if (o1.overlaps(o2) && !collisions.contains(o2)) {
                        // Add object to collided objects
                        collisions.add(o2);

                        // Call collision event
                        TerraEngine.getEventManager().callEvent(new CollisionEvent(o1, o2));
                    } else {
                        collisions.remove(o2);
                    }
                });
            });
        });

    }

    @SuppressWarnings("All")
    private final TMap<World, TMap<TObject, TSet<TObject>>> collisions = new THashMap<>();

    @Nonnull
    @Override
    public Duration getDelay() {
        return ZERO;
    }

    @Nonnull
    @Override
    public Duration getInterval() {
        return ZERO;
    }
}