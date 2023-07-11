package oasis.artemis.task;

import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <h2>Task</h2>
 * <p>A task is executed by a scheduler.</p>
 */
public interface Task {
    /**
     * Executes this task.
     * @param delta Amount of time which has passed between the last execution of this task and now.
     *              If this is the first execution, this will be the duration between registration and now.
     */
    void execute(@Nonnull Duration delta);

    /**
     * Gets delay of this task.
     * @return Delay
     */
    @Nonnull
    Duration getDelay();

    /**
     * Gets the interval of this task.
     * <p>
     * When the value is smaller than the precision of its scheduler,
     * the task will be called every loop.
     * If the value is {@code null}, this task will only be executed once,
     * after the specified delay has passed.
     * </p>
     * <p>
     *     When this is set to {@link Task#ZERO},
     *     it guarantees that this task will be called every loop.
     * </p>
     * @return Interval
     */
    @Nullable
    Duration getInterval();

    Duration ZERO = new Duration(0);
}
