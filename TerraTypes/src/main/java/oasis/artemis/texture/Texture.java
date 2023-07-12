package oasis.artemis.texture;

import oasis.artemis.collection.list.TList;

import javax.annotation.Nonnull;
import java.io.File;

/**
 * <h2>Texture</h2>
 * <p>An in-game texture.</p>
 *
 * @param textures Source file
 * @param profile
 */
public record Texture(
        @Nonnull TList<File> textures,
        @Nonnull Profile profile
) {

}
