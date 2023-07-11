package oasis.artemis.scheduler;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.task.Task;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>Scheduler</h2>
 * <p>
 * A scheduler handles the scheduling and execution of tasks.
 * Tasks in Terra are defined as {@link Task}s.
 * </p>
 */
public class Scheduler {
    /**
     * Creates a new scheduler.
     */
    public Scheduler() {
        //
        // Change ths duration here to adjust the precision of the scheduler.
        // Lower values will offer more real-time processing, but will also worsen performance.
        //

        this(new Duration(15));
    }

    /**
     * Starts the scheduler.
     */
    public void start() {
        thread.start();
    }

    /**
     * Stops the scheduler.
     */
    public void stop() {
        thread.interrupt();
    }

    //
    // Tasks
    //

    /**
     * Registers a task.
     *
     * @param task Task to register
     */
    public void registerTask(@Nonnull Task task) {
        tasks.add(new TaskEntry(task));
    }

    /**
     * Registers multiple tasks.
     *
     * @param tasks Tasks to register
     */
    public void registerTasks(@Nonnull Task... tasks) {
        for (Task t : tasks) registerTask(t);
    }

    /**
     * Registers a list of tasks.
     *
     * @param tasks List of tasks to register
     */
    public void registerTasks(@Nonnull TList<Task> tasks) {
        tasks.forEach(this::registerTask);
    }

    /**
     * Unregisters a task.
     * All tasks with the same signature will be unregistered.
     *
     * @param task Task to unregister
     */
    public void unregisterTask(@Nonnull Task task) {
        tasks.removeIf(e -> e.task.equals(task));
    }

    //
    // Internal processing
    //

    @SuppressWarnings("BusyWait")
    private Scheduler(@Nonnull Duration interval) {
        this.tasks = new TArray<>();

        this.thread = new Thread(() -> {
            while (true) {
                tasks.forEach(entry -> {
                    final Task task = entry.task;

                    final Duration timeSince = new Duration(entry.lastExecution, DateTime.now());

                    if (!entry.started && entry.startAt.isAfterNow()) return;
                    if (timeSince.isShorterThan(task.getInterval())) return;

                    task.execute(timeSince);
                    if (!entry.started) entry.started = true;
                    entry.lastExecution = DateTime.now();
                });

                tasks.removeIf(e -> e.task.getInterval() == null && e.started);

                try {
                    Thread.sleep(interval.getMillis());
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }

    @Nonnull
    private final Thread thread;
    @Nonnull
    private final TList<TaskEntry> tasks;


    private static class TaskEntry {
        public TaskEntry(@Nonnull Task task) {
            this.task = task;
            this.lastExecution = DateTime.now();
            this.startAt = DateTime.now().plus(task.getDelay());
            this.started = false;
        }

        @Nonnull
        private final Task task;
        @Nonnull
        private DateTime lastExecution;
        @Nonnull
        private final DateTime startAt;
        private boolean started;
    }
}
