package oasis.artemis.task.debug;

import oasis.artemis.TerraEngine;
import oasis.artemis.physics.Location;
import oasis.artemis.task.Task;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * Put all {@code System.out.println}s here.
 */
public class DebugTask implements Task {
    @Override
    public void execute(@Nonnull Duration delta) {
        TerraEngine.getState().getWorlds().forEach(w -> {
            w.getObjects().forEach(o -> {
                final Location loc = o.getLocation();
                System.out.println("Location of object is " + loc.x() + " " + loc.y() + " " + loc.z());
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
