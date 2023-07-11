package oasis.artemis;

import oasis.artemis.event.EventManager;
import oasis.artemis.object.RealisticObject;
import oasis.artemis.physics.Location;
import oasis.artemis.physics.Mass;
import oasis.artemis.physics.Metric;
import oasis.artemis.physics.Volume;
import oasis.artemis.scheduler.Scheduler;
import oasis.artemis.state.State;
import oasis.artemis.string.Text;
import oasis.artemis.task.debug.DebugTask;
import oasis.artemis.task.physics.CollisionTask;
import oasis.artemis.task.physics.GravityTask;
import oasis.artemis.task.physics.MovementTask;
import oasis.artemis.world.RealisticWorld;
import oasis.artemis.world.World;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>TerraEngine</h2>
 * <p>The main interface of TerraEngine.</p>
 */
public final class TerraEngine {
    //
    // Instance getters
    //

    /**
     * Gets the scheduler instance.
     * @return {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getScheduler() { return scheduler; }

    /**
     * Gets the event manager instance.
     * @return {@link EventManager}
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the state instance.
     * @return {@link State}
     */
    @Nonnull
    public static State getState() {
        return state;
    }

    //
    // Static methods & variables
    //

    /**
     * The main method of TerraEngine.
     * @param args Arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Register tasks
        registerTasks();

        // Testing out gravity
        final World world = new RealisticWorld(UUID.randomUUID(), new Text("World 1"));
        final Location loc = Location.builder().world(world).build();
        final RealisticObject person = new RealisticObject(UUID.randomUUID(), loc);

        System.out.println(loc.x());
        System.out.println(loc.y());
        System.out.println(loc.z());

        person.setLocation(person.getLocation().plusY(1000));
        person.setVolume(Volume.builder().xyz(Metric.centimeter(45), Metric.centimeter(170), Metric.centimeter(25)).build());
        person.setMass(new Mass(70, Mass.Unit.KILOGRAM));

//        person.setVector(person.getVector().plusX(Metric.kilometersPerHour(300)));

        world.addObject(person);
        state.addWorld(world);

        scheduler.registerTask(new DebugTask());

        // Start scheduler
        startScheduler();
    }

    // Registers all tasks
    private static void registerTasks() {
        // Physics
        scheduler.registerTask(new CollisionTask());
        scheduler.registerTask(new GravityTask());
        scheduler.registerTask(new MovementTask());
    }

    // Starts the scheduler
    private static void startScheduler() {
        scheduler.start();
    }

    // Scheduler
    private static final Scheduler scheduler = new Scheduler();

    // Event manager
    private static final EventManager eventManager = new EventManager();

    // State
    private static final State state = new State();
}