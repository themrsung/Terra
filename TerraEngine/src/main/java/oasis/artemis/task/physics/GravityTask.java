package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
import oasis.artemis.object.ImmovableObject;
import oasis.artemis.object.TObject;
import oasis.artemis.physics.Physics;
import oasis.artemis.task.Task;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <h2>GravityTask</h2>
 * <p>
 * Handles gravity.
 * </p>
 */
public final class GravityTask implements Task {
    @Override
    public void execute(@Nonnull Duration delta) {
        TerraEngine.getState().getWorlds().forEach(world -> {
            world.getObjects().filter(TObject::obeysPhysics).forEach(object -> {
                AtomicBoolean isOnImmovable = new AtomicBoolean(false);

                world.getObjects().filter(ImmovableObject.class).forEach(immovable -> {
                    if (Physics.isStandingOn(object, immovable)) {
                        isOnImmovable.set(true);
                    }
                });

                if (!isOnImmovable.get()) {
                    final double gravity = Physics.gravity(world, delta);
                    object.setVector(object.getVector().plusY(-gravity));
                }
            });
        });

    }

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
