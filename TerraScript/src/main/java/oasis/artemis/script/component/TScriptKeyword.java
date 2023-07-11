package oasis.artemis.script.component;

import javax.annotation.Nonnull;

/**
 * <h2>ScripKeyword</h2>
 * <p></p>
 */
public enum TScriptKeyword {
    // Variables
    POINTER("pointer"),
    STRING("string"),
    INTEGER("int"),
    BOOLEAN("boolean"),
    FUNCTION("function"),
    LIST("list"),

    // Statements
    IF("if"),
    FOR("for"),
    WHILE("while"),
    BREAK("break"),
    RETURN("return"),

    ;

    TScriptKeyword(@Nonnull String label) {
        this.label = label;
    }

    private final String label;

    @Nonnull
    public String getLabel() {
        return label;
    }
}
