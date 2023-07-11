package oasis.artemis.script.component;

import oasis.artemis.string.TString;

import javax.annotation.Nonnull;

/**
 * <h2>TScriptComponent</h2>
 * <p></p>
 */
public record TScriptComponent(
        @Nonnull TString line,
        @Nonnull TScriptKeyword keyword
) {

}
