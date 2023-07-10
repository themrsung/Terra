package oasis.artemis.util;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>Unique</h2>
 * <p>A unique type has a {@link UUID} as a unique identifier.</p>
 */
public interface Unique {
    /**
     * Gets the unique identifier of this instance.
     *
     * @return Unique ID
     */
    @Nonnull
    UUID getUniqueId();
}
