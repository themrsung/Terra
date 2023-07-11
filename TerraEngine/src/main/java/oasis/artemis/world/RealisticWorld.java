package oasis.artemis.world;

import oasis.artemis.string.TString;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>RealisticWorld</h2>
 * <p>
 * A realistic world has Earth-like properties.
 * Specific values can be changed after creation, but the default values are set to Earth's constants.
 * </p>
 * <p>
 * Realistic worlds check for collisions between events every tick.
 * If a collision is detected, a CollisionEvent is called.
 * </p>
 */
public class RealisticWorld extends AbstractWorld {
    public RealisticWorld(@Nonnull UUID uniqueId, @Nonnull TString name) {
        super(uniqueId, name);
    }

    @Override
    public void tick(@Nonnull Duration delta) {
    }
}
