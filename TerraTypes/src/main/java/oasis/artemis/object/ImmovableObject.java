package oasis.artemis.object;

import oasis.artemis.physics.Location;
import oasis.artemis.physics.Mass;
import oasis.artemis.physics.Vector;
import oasis.artemis.physics.Volume;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>ImmovableObject</h2>
 * <p>
 * Immovable objects are used to represent grounds, buildings, etc.
 * Their mass is fixed to {@link Mass#INFINITE}, and {@link ImmovableObject#setLocation(Location)}
 * will do nothing.
 * Vectors are also fixed to {@link Vector#ZERO}, and {@link ImmovableObject#setVector(Vector)}
 * will do nothing.
 * </p>
 * <p>
 * Immovable objects are guaranteed to never change location.
 * Due to this property, their location cannot be changed after creation,
 * even by Terra itself.
 * </p>
 * <p>
 * Immovable objects can still obey physics, but their location will not change.
 * </p>
 * <p>
 * The drag coefficient of immovable objects is {@link TObject#DEFAULT_DRAG_COEFFICIENT},
 * and {@link ImmovableObject#setDragCoefficient(double)} will do nothing.
 * </p>
 */
public class ImmovableObject extends AbstractObject {
    /**
     * Default minimal constructor.
     *
     * @param uniqueId Unique ID of this object
     * @param location Location of this object
     */
    public ImmovableObject(@Nonnull UUID uniqueId, @Nonnull Location location) {
        super(uniqueId, location);
    }

    /**
     * Default normal constructor.
     *
     * @param uniqueId Unique ID of this object
     * @param location Location of this object
     * @param volume   Volume of this object
     */
    public ImmovableObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Volume volume) {
        super(uniqueId, location, Mass.INFINITE, volume);
    }

    /**
     * Default all-args constructor.
     *
     * @param uniqueId     Unique ID of this object
     * @param location     Location of this object
     * @param volume       Volume of this object
     * @param fluid        Whether this object is a fluid
     * @param obeysPhysics Whether this object obeys physics
     */
    public ImmovableObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Volume volume, boolean fluid, boolean obeysPhysics) {
        super(uniqueId, location, Mass.INFINITE, volume, fluid, obeysPhysics);
    }

    /**
     * Default copy constructor.
     *
     * @param other Object to copy
     */
    public ImmovableObject(@Nonnull ImmovableObject other) {
        super(other);
    }

    @Nonnull
    @Override
    public Mass getMass() {
        return Mass.INFINITE;
    }

    @Nonnull
    @Override
    public Vector getVector() { return Vector.ZERO; }

    @Override
    public void setMass(@Nonnull Mass mass) {}

    @Override
    public void setLocation(@Nonnull Location location) {}

    @Override
    public void setVector(@Nonnull Vector vector) {}

    @Override
    public double getDragCoefficient() {
        return DEFAULT_DRAG_COEFFICIENT;
    }

    @Override
    public void setDragCoefficient(double dragCoefficient) {}
}
