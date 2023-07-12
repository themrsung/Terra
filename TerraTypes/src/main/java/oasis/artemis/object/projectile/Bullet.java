package oasis.artemis.object.projectile;

import oasis.artemis.object.TransientObject;
import oasis.artemis.physics.Location;
import oasis.artemis.physics.Mass;
import oasis.artemis.physics.Vector;
import oasis.artemis.physics.Volume;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * <h2>Bullet</h2>
 * <p>A projectile with default profiles.</p>
 */
public class Bullet extends TransientObject implements Projectile {

    /**
     * Round profiles.
     * All denotations of barrel length include the suppressor length.
     */
    public enum Rounds {
        /**
         * Standard supersonic 9mm parabellum round fired from a Glock 17.
         */
        NINE_MILLIMETER_PARABELLUM_SUPER,

        /**
         * Standard subsonic 9mm parabellum round fired from a Glock 17.
         */
        NINE_MILLIMETER_PARABELLUM_SUB,

        /**
         * Standard supersonic .223 Remington round fired from a 16" barrel.
         */
        TWO_TWO_THREE_REMINGTON_SUPER,

        /**
         * Standard supersonic 5.56mm NATO round fired from a 16" barrel.
         */
        FIVE_FIVE_SIX_NATO_SUPER,

        /**
         * Standard supersonic .300 BLK round fired from an 11" barrel.
         */
        THREE_HUNDRED_BLACKOUT_SUPER,

        /**
         * Standard subsonic .300 BLK round fired from an 11" barrel.
         */
        THREE_HUNDRED_BLACKOUT_SUB,

        /**
         * Standard supersonic 7.62 NATO round fired from a 16" barrel.
         */
        SEVEN_SIX_TWO_SUPER,

        /**
         * .50 BMG round fired from a 30" barrel.
         */
        FIFTY_CAL_BMG,

        /**
         * .50 AE round fired from a Desert Eagle (6" barrel).
         */
        FIFTY_ACTION_EXPRESS;

    }


    public Bullet(@Nonnull UUID uniqueId, @Nonnull Location location, @Nullable Shooter shooter) {
        super(uniqueId, location);
        this.shooter = shooter;
    }

    public Bullet(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume, @Nullable Shooter shooter) {
        super(uniqueId, location, mass, volume);
        this.shooter = shooter;
    }

    public Bullet(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid, boolean obeysPhysics, @Nullable Shooter shooter) {
        super(uniqueId, location, mass, volume, fluid, obeysPhysics);
        this.shooter = shooter;
    }

    public Bullet(@Nonnull UUID uniqueId, @Nonnull Location location, @Nonnull Vector vector, @Nonnull Mass mass, @Nonnull Volume volume, boolean fluid, boolean obeysPhysics, double dragCoefficient, @Nullable Shooter shooter) {
        super(uniqueId, location, vector, mass, volume, fluid, obeysPhysics, dragCoefficient);
        this.shooter = shooter;
    }

    public Bullet(@Nonnull Bullet other) {
        super(other);
        this.shooter = other.shooter;
    }

    @Nullable
    private final Shooter shooter;

    @Override
    @Nullable
    public Shooter getShooter() {
        return shooter;
    }
}
