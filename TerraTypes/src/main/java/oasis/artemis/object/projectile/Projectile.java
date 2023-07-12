package oasis.artemis.object.projectile;

import oasis.artemis.object.TObject;

import javax.annotation.Nullable;

/**
 * <h2>Projectile</h2>
 * <p>A shootable object. Shot by {@link Shooter}s.</p>
 */
public interface Projectile extends TObject {
    @Nullable
    Shooter getShooter();
}
