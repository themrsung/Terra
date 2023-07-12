package oasis.artemis;

import oasis.artemis.event.EventManager;
import oasis.artemis.graphics.TerraGraphics;
import oasis.artemis.listener.physics.CollisionListener;
import oasis.artemis.object.ImmovableObject;
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
import oasis.artemis.task.physics.ResistanceTask;
import oasis.artemis.task.tick.TickTask;
import oasis.artemis.world.RealisticWorld;
import oasis.artemis.world.World;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>TerraEngine</h2>
 * <p>The main class of TerraEngine.</p>
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
     * Gets the ticker instance.
     * @return {@link TickTask}
     */
    @Nonnull
    public static TickTask getTicker() {
        return ticker;
    }

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

    /**
     * Gets the graphics instance.
     *
     * @return {@link TerraGraphics}
     */
    @Nonnull
    public static TerraGraphics getGraphics() {
        return graphics;
    }

    //
    // Static methods & variables
    //

    /**
     * The main method of TerraEngine.
     *
     * @param args Arguments
     */
    public static void main(@Nonnull String[] args) {
        System.out.println("Hello world!");

        //
        // DEBUG
        //

        // Testing out gravity
        final World world = new RealisticWorld(UUID.randomUUID(), new Text("World 1"));
        final Location loc = Location.builder().world(world).build();
        final RealisticObject person = new RealisticObject(UUID.randomUUID(), loc);

        person.setLocation(person.getLocation().plusY(555));
        person.setVolume(Volume.builder().xyz(Metric.centimeter(20), Metric.centimeter(170), Metric.centimeter(15)).build());
        person.setMass(new Mass(70, Mass.Unit.KILOGRAM));

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

        //
        // DEBUG
        //

        start();
    }

    /**
     * Starts the engine.
     */
    public static void start() {
        // Register tasks
        registerTasks();

        // Register tickables
        registerTickables();

        // Register listeners
        registerListeners();

        // Start scheduler
        startScheduler();

        // Start graphics
        graphics.onEngineStarted();

    }

    /**
     * Stops the engine.
     */
    public static void stop() {
        scheduler.stop();
        graphics.onEngineStopped();
    }

    // Registers all tasks
    private static void registerTasks() {
        // Physics
        scheduler.registerTask(new CollisionTask());
        scheduler.registerTask(new GravityTask());
        scheduler.registerTask(new MovementTask());
        scheduler.registerTask(new ResistanceTask());

        // Tick
        scheduler.registerTask(ticker);
    }

    // Registers all listeners
    private static void registerListeners() {
        eventManager.registerListener(new CollisionListener());
    }

    // Registers all tickables
    private static void registerTickables() {
        ticker.registerTickable(state);
        ticker.registerTickable(graphics);
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

    // Ticker
    private static final TickTask ticker = new TickTask();

    // Graphics
    private static final TerraGraphics graphics = new TerraGraphics();
}