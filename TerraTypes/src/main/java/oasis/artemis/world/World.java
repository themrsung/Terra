package oasis.artemis.world;

import oasis.artemis.collection.set.TSet;
import oasis.artemis.object.TObject;
import oasis.artemis.string.TString;
import oasis.artemis.util.Tickable;
import oasis.artemis.util.Unique;

import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * <h2>World</h2>
 * <p>
 * A world represents an in-game level.
 * There can be multiple worlds in one game state.
 * </p>
 */
public interface World extends Unique, Tickable, Serializable {
    //
    // Identification
    //

    /**
     * Gets the name of this world.
     *
     * @return Name
     */
    @Nonnull
    TString getName();

    /**
     * Sets the name of this world.
     *
     * @param name Name
     */
    void setName(@Nonnull TString name);

    //
    // Objects
    //

    /**
     * Gets a set of objects in this world.
     *
     * @return set of objects
     */
    @Nonnull
    TSet<TObject> getObjects();

    /**
     * Adds an object to this world.
     *
     * @param object Object to add
     */
    void addObject(@Nonnull TObject object);

    /**
     * Removes an object from this world.
     *
     * @param object Object to remove
     */
    void removeObject(@Nonnull TObject object);

    //
    // Physics
    //

    /**
     * The default gravity.
     */
    double DEFAULT_GRAVITY = 9.807d;

    /**
     * The default air density.
     */
    double DEFAULT_AIR_DENSITY = 1.204d;

    /**
     * The default ground level.
     */
    double DEFAULT_GROUND_LEVEL = 0;

    /**
     * Gets the gravity of this world, denoted in meters per second squared.
     *
     * @return Gravity
     */
    double getGravity();

    /**
     * Gets the air density of this world, denoted in kilograms per cubic meter.
     *
     * @return Air density in kg/m3
     */
    @Positive
    double getAirDensity();

    /**
     * Gets the Y level of this world which is considered to be ground.
     *
     * @return Ground level
     */
    double getGroundLevel();

    /**
     * Sets the gravity of this world, denoted in meters per second squared.
     *
     * @param gravity Gravity
     */
    void setGravity(double gravity);

    /**
     * Sets the air density of this world, denoted in kilograms per cubic meter.
     * Density cannot be zero, as it will break physics calculations.
     * For an air-less, world set this to {@link Double#MIN_VALUE}.
     *
     * @param density Density in kg/m3
     */
    void setAirDensity(@Positive double density);

    /**
     * Sets the Y level of this world which is considered to be ground.
     *
     * @param level Y coordinate of ground level
     */
    void setGroundLevel(double level);
}
