package oasis.artemis.task.debug;

import oasis.artemis.TerraEngine;
import oasis.artemis.object.RealisticObject;
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
                if (o instanceof RealisticObject) {
                    System.out.println("Location of object " + o.getUniqueId().toString().substring(0, 5) + " is " + loc.x() + " " + loc.y() + " " + loc.z());
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
        return new Duration(1000);
    }
}
