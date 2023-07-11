package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
import oasis.artemis.object.TObject;
import oasis.artemis.physics.Physics;
import oasis.artemis.task.Task;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <h2>GravityTask</h2>
 * <p>
 *     Handles gravity.
 * </p>
 */
public final class GravityTask implements Task {
    @Override
    public void execute(@Nonnull Duration delta) {
        TerraEngine.getState().getWorlds().forEach(world -> {
            world.getObjects().filter(TObject::obeysPhysics).forEach(object -> {
                final double gravity = Physics.gravity(world, delta);
                object.setVector(object.getVector().plusY(-gravity));
            });
        });

    }

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
