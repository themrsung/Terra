package oasis.artemis.object.projectile;

import oasis.artemis.object.TObject;
import oasis.artemis.physics.Location;
import oasis.artemis.physics.Vector;

import javax.annotation.Nonnull;

/**
 * <h2>Shooter</h2>
 * <p>An object which can shoot {@link Projectile}s.</p>
 */
public interface Shooter extends TObject {
    /**
     * Shoots a projectile.
     *
     * @param projectile Projectile to shoot
     */
    void shoot(@Nonnull Projectile projectile);

    /**
     * Gets the point at which projectiles fired from this event should start.
     *
     * @return Barrel location
     */
    @Nonnull
    Location getBarrel();

    /**
     * Gets the direction in which to shoot projectiles in.
     *
     * @return {@link Vector} containing the relative direction to shoot projectiles in
     */
    @Nonnull
    Vector getBarrelVector();

    /**
     * Sets the direction in which to shoot projectiles in.
     *
     * @param vector Relative direction to shoot projectiles to
     */
    void setBarrelVector(@Nonnull Vector vector);
}
