package oasis.artemis.script;

import oasis.artemis.collection.list.TList;
import oasis.artemis.script.component.TScriptComponent;

import javax.annotation.Nonnull;

/**
 * <h2>TerraScript</h2>
 * <p>An instance of a script.</p>
 */
public class TerraScript {
    public TerraScript(@Nonnull TList<TScriptComponent> components) {
        this.components = components;
    }

    public void execute() {
        return;
    }

    @Nonnull
    private final TList<TScriptComponent> components;

    @Nonnull
    public TList<TScriptComponent> getComponents() {
        return components;
    }
}
