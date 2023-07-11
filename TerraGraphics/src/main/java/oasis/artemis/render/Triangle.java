package oasis.artemis.render;

import javax.annotation.Nonnull;
import java.awt.*;

/**
 * <h2>Triangle</h2>
 *
 * @param v1    Vertex 1 of this triangle
 * @param v2    Vertex 2 of this triangle
 * @param v3    Vertex 3 of this triangle
 * @param color Color of this triangle
 */
public record Triangle(
        @Nonnull Vertex v1,
        @Nonnull Vertex v2,
        @Nonnull Vertex v3,
        @Nonnull Color color
) {

}
