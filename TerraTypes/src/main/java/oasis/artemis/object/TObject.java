package oasis.artemis.object;

import oasis.artemis.physics.*;
import oasis.artemis.texture.Texture;
import oasis.artemis.util.Unique;
import oasis.artemis.world.World;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * <h2>TObject</h2>
 * <p>
 * A TObject represents an object that can be placed in a world.
 * TObjects are simply referred to as objects outside of this file.
 * </p>
 */
public interface TObject extends Unique, Serializable {
    //
    // Constants
    //
    double DEFAULT_DRAG_COEFFICIENT = 1.0;

    //
    // Physics
    //

    /**
     * Gets the location of this object.
     *
     * @return {@link Location}
     */
    @Nonnull
    Location getLocation();

    /**
     * Gets the world this object is in.
     *
     * @return {@link World}
     */
    @Nonnull
    default World getWorld() {return getLocation().world();}

    /**
     * Gets the vector of this object.
     *
     * @return {@link Vector}
     */
    @Nonnull
    Vector getVector();

    /**
     * Gets the velocity of this object, denoted in meters per second.
     *
     * @return Velocity
     */
    @Nonnegative
    default double getVelocity() {return getVector().getMagnitude();}

    /**
     * Gets the mass of this object.
     *
     * @return {@link Mass}
     */
    @Nonnull
    Mass getMass();

    /**
     * Gets the mass of this object, denoted in kilograms.
     *
     * @return {@link Mass#valueKilograms()}
     */
    @Nonnegative
    default double getMassKilograms() {return getMass().valueKilograms();}

    /**
     * Gets the volume of this object.
     *
     * @return {@link Volume}
     */
    @Nonnull
    Volume getVolume();

    /**
     * Gets the TriLocation of this object.
     * This represents the amount of three-dimensional space this object exists in.
     *
     * @return {@link TriLocation}
     */
    @Nonnull
    default TriLocation getTriLocation() {return new TriLocation(getLocation(), getVolume());}

    /**
     * Checks if this object contains another object in spacial context.
     *
     * @param other Other object
     * @return {@code true} if the other object's TriLocation is within the bounds of this object's TriLocation
     * @see TriLocation#contains(TriLocation)
     */
    default boolean contains(@Nonnull TObject other) {return getTriLocation().contains(other.getTriLocation());}

    /**
     * Checks if this object overlaps another object in spacial context.
     *
     * @param other Other object
     * @return {@code true} if the other object's TriLocation is within the bounds of this object's TriLocation
     * @see TriLocation#overlaps(TriLocation)
     */
    default boolean overlaps(@Nonnull TObject other) {return getTriLocation().overlaps(other.getTriLocation());}

    /**
     * Gets the density of this object, denoted in kilograms per cubic meter.
     *
     * @return Density (kg/m3)
     */
    default double getDensity() {
        try {
            return getMass().valueKilograms() / getVolume().getVolume();
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    /**
     * Gets whether this object allows other objects to overlap with its bounds.
     *
     * @return {@code true} if this is a fluid.
     */
    boolean isFluid();

    /**
     * Whether this object is subject to gravity and air resistance.
     *
     * @return {@code true} if this actor obeys physics.
     */
    boolean obeysPhysics();

    /**
     * Gets the drag coefficient of this object.
     * This is used to calculate air resistance.
     *
     * @return Drag coefficient
     */
    @Positive
    double getDragCoefficient();

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Location location);

    /**
     * Sets the vector of this object.
     *
     * @param vector Vector
     */
    void setVector(@Nonnull Vector vector);

    /**
     * Sets the mass of this object.
     *
     * @param mass Mass
     */
    void setMass(@Nonnull Mass mass);

    /**
     * Sets the volume of this object.
     *
     * @param volume Volume
     */
    void setVolume(@Nonnull Volume volume);

    /**
     * Sets whether this object is a fluid.
     *
     * @param fluid {@code true} for fluids
     */
    void setFluid(boolean fluid);

    /**
     * Sets whether this object is subject to gravity and air resistance.
     *
     * @param obeysPhysics {@code true} to obey physics
     */
    void setObeysPhysics(boolean obeysPhysics);

    /**
     * Sets the drag coefficient of this object.
     *
     * @param coefficient Drag coefficient
     */
    void setDragCoefficient(@Positive double coefficient);

    //
    // Rendering
    //
    @Nullable
    Texture getTexture();
}
