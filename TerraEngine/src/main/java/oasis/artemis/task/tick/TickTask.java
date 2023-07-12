package oasis.artemis.task.tick;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.task.Task;
import oasis.artemis.util.Tickable;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.Comparator;

/**
 * <h2>TickTask</h2>
 * <p>Calls every {@link Tickable} once per tick.</p>
 */
public final class TickTask implements Task {
    public void registerTickable(@Nonnull Tickable tickable) {
        tickables.add(tickable);
        tickables.sort(Comparator.comparing(Tickable::getPriority));
    }

    public void unregisterTickable(@Nonnull Tickable tickable) {
        tickables.remove(tickable);
    }

    @Override
    public void execute(@Nonnull Duration delta) {
        tickables.forEach(t -> t.tick(delta));
    }

    private final TList<Tickable> tickables = new TArray<>();

    @Nonnull
    @Override
    public Duration getInterval() {
        return Duration.ZERO;
    }

    @Nonnull
    @Override
    public Duration getDelay() {
        return Duration.ZERO;
    }
}
