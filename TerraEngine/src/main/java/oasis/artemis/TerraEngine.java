package oasis.artemis;

import oasis.artemis.event.EventManager;
import oasis.artemis.listener.physics.CollisionListener;
import oasis.artemis.object.ImmovableObject;
import oasis.artemis.object.RealisticObject;
import oasis.artemis.physics.*;
import oasis.artemis.scheduler.Scheduler;
import oasis.artemis.state.State;
import oasis.artemis.string.Text;
import oasis.artemis.task.debug.DebugTask;
import oasis.artemis.task.physics.CollisionTask;
import oasis.artemis.task.physics.GravityTask;
import oasis.artemis.task.physics.MovementTask;
import oasis.artemis.task.physics.ResistanceTask;
import oasis.artemis.util.ObjectPair;
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
     *
     * @return {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getScheduler() {return scheduler;}

    /**
     * Gets the event manager instance.
     *
     * @return {@link EventManager}
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the state instance.
     *
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
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Register tasks
        registerTasks();

        // Register listeners
        registerListeners();

        // Testing out gravity
        final World world = new RealisticWorld(UUID.randomUUID(), new Text("World 1"));
        final Location loc = Location.builder().world(world).build();
        final RealisticObject person = new RealisticObject(UUID.randomUUID(), loc);

        person.setLocation(person.getLocation().plusY(555));
        person.setVolume(Volume.builder().xyz(Metric.centimeter(20), Metric.centimeter(170), Metric.centimeter(15)).build());
        person.setMass(new Mass(70, Mass.Unit.KILOGRAM));

//        person.setVector(person.getVector().plusX(1));

//        person.setVector(person.getVector().plusX(Metric.kilometersPerHour(300)));

        world.addObject(person);
        state.addWorld(world);

        scheduler.registerTask(new DebugTask());

        // Ground
        final ImmovableObject ground = new ImmovableObject(UUID.randomUUID(), Location.builder()
                .world(world)
                .y(-10000)
                .build());

        ground.setObeysPhysics(true);

        ground.setVolume(new Volume(Double.MAX_VALUE, 20000, Double.MAX_VALUE));
        world.addObject(ground);

        // Vector test
        Vector test1 = new Vector(1, 1, 1);
        Vector test2 = new Vector(1, -1, -1);
        Vector test3 = new Vector(-1, 1, 1);
        Vector test4 = new Vector(-1, -1, -1);
        Vector test5 = new Vector(-1, 0, 1);

        System.out.println("VECTOR TEST " + test1.getYaw());
        System.out.println("VECTOR TEST " + test2.getYaw());
        System.out.println("VECTOR TEST " + test3.getYaw());
        System.out.println("VECTOR TEST " + test4.getYaw());
        System.out.println("VECTOR TEST " + test5.getYaw());

        // Collision
//        final RealisticObject o1 = new RealisticObject(UUID.randomUUID(), Location.builder().world(world).build());
//        final RealisticObject o2 = new RealisticObject(UUID.randomUUID(), Location.builder().world(world).build());
//
//        o1.setMass(new Mass(10, Mass.Unit.KILOGRAM));
//        o2.setMass(new Mass(10, Mass.Unit.KILOGRAM));
//
//        o1.setVolume(new Volume(10, 10, 10));
//        o2.setVolume(new Volume(10, 10, 10));
//
//        o1.setLocation(o1.getLocation().plusX(100));
//        o2.setLocation(o1.getLocation().plusX(-100));
//
//        o1.setVector(o1.getVector().plusX(Metric.kilometersPerHour(-60)));
//        o2.setVector(o2.getVector().plusX(Metric.kilometersPerHour(60)));
//
//        world.addObject(o1);
//        world.addObject(o2);

        // Start scheduler
        startScheduler();
    }

    // Registers all tasks
    private static void registerTasks() {
        // Physics
        scheduler.registerTask(new CollisionTask());
        scheduler.registerTask(new GravityTask());
        scheduler.registerTask(new MovementTask());
        scheduler.registerTask(new ResistanceTask());
    }

    // Registers all listeners
    private static void registerListeners() {
        eventManager.registerListener(new CollisionListener());
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