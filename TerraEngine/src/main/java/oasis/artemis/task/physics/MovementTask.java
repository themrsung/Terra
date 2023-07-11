package oasis.artemis.task.physics;

import oasis.artemis.TerraEngine;
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
                Vector acceleration = object.getVector().modifyAll(0.001).modifyAll(delta.getMillis());

                // X terminal velocity
                final double xVelocity = Math.abs(acceleration.x());
                final double terminalX = Physics.terminalVelocity(object, Vector.FRONT);
                if (xVelocity > terminalX) {
                    acceleration = acceleration.setX(xVelocity > 0 ? terminalX : -terminalX);
                }

                // Y terminal velocity
                final double yVelocity = Math.abs(acceleration.y());
                final double terminalY = Physics.terminalVelocity(object, Vector.UP);
                if (yVelocity > terminalY) {
                    acceleration = acceleration.setY(yVelocity > 0 ? terminalY : -terminalY);
                }

                // Z terminal velocity
                final double zVelocity = Math.abs(acceleration.z());
                final double terminalZ = Physics.terminalVelocity(object, Vector.RIGHT);
                if (zVelocity > terminalZ) {
                    acceleration = acceleration.setZ(zVelocity > 0 ? terminalZ : -terminalZ);
                }

                object.setLocation(object.getLocation().plusVector(acceleration));
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
