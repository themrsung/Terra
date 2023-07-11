package oasis.artemis.object;

import oasis.artemis.physics.Location;
import oasis.artemis.physics.Mass;
import oasis.artemis.physics.Vector;
import oasis.artemis.physics.Volume;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>RealisticObject</h2>
 * <p>
 * A realistic object obeys physics.
 * </p>
 */
public class RealisticObject extends AbstractObject {
    /**
     * Default minimal constructor.
     *
     * @param uniqueId Unique ID of this object
     * @param location Location of this object
     */
    public RealisticObject(@Nonnull UUID uniqueId, @Nonnull Location location) {
        super(uniqueId, location);
    }

    /**
     * Default normal constructor.
     *
     * @param uniqueId Unique ID of this object
     * @param location Location of this object
     * @param mass     Mass of this object
     * @param volume   Volume of this object
     */
    public RealisticObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume) {
        super(uniqueId, location, mass, volume);
    }

    /**
     * Default semi-maximal constructor.
     *
     * @param uniqueId Unique ID of this object
     * @param location Location of this object
     * @param mass     Mass of this object
     * @param volume   Volume of this object
     * @param fluid    Whether this object is a fluid
     */
    public RealisticObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid) {
        super(uniqueId, location, mass, volume, fluid, true);
    }

    /**
     * Default all-args-constructor.
     *
     * @param uniqueId        Unique ID of this object
     * @param location        Location of this object
     * @param vector          Vector of this object
     * @param mass            Mass of this object
     * @param volume          Volume of this object
     * @param fluid           Whether this object is a fluid
     * @param dragCoefficient Drag coefficient of this object
     */
    public RealisticObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Vector vector, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid, double dragCoefficient) {
        super(uniqueId, location, vector, mass, volume, fluid, true, dragCoefficient);
    }

    public RealisticObject(@Nonnull RealisticObject other) {super(other);}

    /**
     * A realistic object cannot disobey physics.
     * This method will do nothing.
     *
     * @param obeysPhysics {@code false} will throw an exception
     * @throws IllegalArgumentException When given value is false
     */
    @Override
    public void setObeysPhysics(boolean obeysPhysics) throws IllegalArgumentException {
        if (!obeysPhysics) throw new IllegalArgumentException();
    }
}
