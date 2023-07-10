package oasis.artemis.object;

import oasis.artemis.physics.*;
import oasis.artemis.util.Unique;

import javax.annotation.Nonnull;
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
     * Gets the vector of this object.
     *
     * @return {@link Vector}
     */
    @Nonnull
    Vector getVector();

    /**
     * Gets the mass of this object.
     *
     * @return {@link Mass}
     */
    @Nonnull
    Mass getMass();

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
     * This cannot return 0, as it will break physics calculations.
     * If this object has no mass or no volume, it will return {@link Double#MIN_VALUE}.
     *
     * @return Density (kg/m3)
     */
    @Positive
    default double getDensity() {
        try {
            return Math.max(getMass().valueKilograms() / getVolume().getVolume(), Double.MIN_VALUE);
        } catch (ArithmeticException e) {
            return Double.MIN_VALUE;
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

}
