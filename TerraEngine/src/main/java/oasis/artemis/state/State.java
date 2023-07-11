package oasis.artemis.state;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;
import oasis.artemis.world.World;

import javax.annotation.Nonnull;

/**
 * <h2>State</h2>
 * <p>The running state of Terra.</p>
 */
public class State {
    public State() {
        this.worlds = new TArray<>();
    }

    /**
     * Gets a list of all worlds present in this state.
     *
     * @return List of worlds
     */
    @Nonnull
    public TList<World> getWorlds() {
        return worlds;
    }

    /**
     * Adds a world to this state.
     *
     * @param world World to add
     */
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
    }

    /**
     * Removes a world from this state.
     *
     * @param world World to remove
     */
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
    }

    @Nonnull
    private final TList<World> worlds;
}
