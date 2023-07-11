package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
import oasis.artemis.physics.Face;
import oasis.artemis.physics.Physics;
import oasis.artemis.physics.Vector;
import oasis.artemis.task.Task;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>MovementTask</h2>
 * <p>
 * Handles the movement of objects.
 * </p>
 */
public final class MovementTask implements Task {
    @Override
    public void execute(@Nonnull Duration delta) {
        TerraEngine.getState().getWorlds().forEach(world -> {
            world.getObjects().forEach(object -> {
                final double terminalVelocity = Physics.terminalVelocity(object, Face.NEGATIVE_Y);
                Vector acceleration = object.getVector().modifyAll(0.001).modifyX(delta.getMillis());

                if (acceleration.getVelocity() > terminalVelocity) {
                    final double adjustment = acceleration.getVelocity() / terminalVelocity;
                    acceleration = acceleration.modifyAll(adjustment);
                }

                object.setLocation(object.getLocation().plusVector(acceleration));
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
