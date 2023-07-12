package oasis.artemis.object;

import oasis.artemis.physics.Location;
import oasis.artemis.physics.Mass;
import oasis.artemis.physics.Vector;
import oasis.artemis.physics.Volume;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>TransientObject</h2>
 * <p>
 *     An object designed to be not saved to data.
 *     For example, a bullet is a transient object.
 * </p>
 */
public class TransientObject extends AbstractObject {
    public TransientObject(@Nonnull UUID uniqueId, @Nonnull Location location) {
        super(uniqueId, location);
    }

    public TransientObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume) {
        super(uniqueId, location, mass, volume);
    }

    public TransientObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid, boolean obeysPhysics) {
        super(uniqueId, location, mass, volume, fluid, obeysPhysics);
    }

    public TransientObject(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Vector vector, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid, boolean obeysPhysics, double dragCoefficient) {
        super(uniqueId, location, vector, mass, volume, fluid, obeysPhysics, dragCoefficient);
    }

    public TransientObject(@Nonnull TransientObject other) {
        super(other);
    }
}
