package oasis.artemis.string;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.function.Function;
import java.util.regex.PatternSyntaxException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h2>TString</h2>
 * <p>
 * A {@link String} with additional features.
 * Because String is final, {@link TString} has a nested String inside,
 * and all utility methods delegate to the nested String.
 * </p>
 * <p>
 * All Vortex types use {@link TString} instead of {@link String}.
 * </p>
 */
public interface TString {
    /**
     * Gets a blank text builder.
     *
     * @return Text builder
     */
    static Text.Builder text() {
        return Text.builder();
    }

    /**
     * Gets a text builder with specified raw string.
     *
     * @param rawString Raw string
     * @return Text builder
     */
    static Text.Builder text(@Nonnull String rawString) {
        return Text.builder(rawString);
    }

    /**
     * Gets a TString with no data. Uses instance of {@link Text}.
     * Use instead of null.
     *
     * @return Blank string
     */
    static TString blank() {
        return new Text();
    }

    /**
     * Creates a new TString from multiple raw strings.
     * Uses an instance of {@link Text}.
     *
     * @param rawStrings Strings
     * @return Resulting {@link TString}
     */
    static TString of(@Nonnull String... rawStrings) {
        TList<TString> strings = new TArray<>();
        for (String s : rawStrings) strings.add(new Text(s));
        return new Text().append(strings);
    }

    /**
     * Joins given list of strings into single string.
     * Uses instance of {@link Text}.
     *
     * @param delimiter Delimiter
     * @param elements  Elements to join
     * @return Joined string
     */
    static TString join(@Nonnull TString delimiter, @Nonnull TList<TString> elements) {
        final String rawDelimiter = delimiter.getRawString();
        final TList<String> rawElements = new TArray<>();

        elements.forEach(s -> rawElements.add(s.getRawString()));

        return of(String.join(rawDelimiter, rawElements.toArray(new String[0])));
    }

    /**
     * Gets the raw string contained in this {@link TString}.
     *
     * @return {@link String}
     */
    @Nonnull
    String getRawString();

    /**
     * Gets the color of this string.
     *
     * @return {@link Color}
     */
    @Nullable
    Color getColor();

    /**
     * Gets the format of this string.
     * This will default to {@link Format#NORMAL} when value is {@code null}.
     *
     * @return {@link Format}
     */
    @Nonnull
    Format getFormat();

    /**
     * Changes the raw string contained in this {@link TString}.
     *
     * @param rawString Raw string to set to
     * @return Resulting string
     */
    @Nonnull
    TString setRawString(@Nonnull String rawString);

    /**
     * Changes the color of this string.
     *
     * @param color Color
     * @return Resulting string
     */
    @Nonnull
    TString setColor(@Nullable Color color);

    /**
     * Changes the format of this string.
     *
     * @param format Format
     * @return Resulting string
     */
    @Nonnull
    TString setFormat(@Nonnull Format format);

    enum Format {
        NORMAL,
        BOLD,
        ITALIC,
        STRIKE,
        UNDERLINED
    }

    //
    // Comparison
    //

    /**
     * Checks if this string is equal to the other.
     * Ignores color and format.
     *
     * @param other Other string
     * @return {@code true} if the contents are the same
     */
    boolean equals(@Nonnull TString other);

    /**
     * Checks if this string's color and format is equal to the other.
     * Ignores contents.
     *
     * @param other Other string
     * @return {@code true} if the formatting is the same.
     */
    boolean equalsFormat(@Nonnull TString other);

    /**
     * Checks if this string is equal to the other.
     * Includes color and format.
     *
     * @param other Other string
     * @return {@code true} if the contents and formatting are the same
     */
    boolean equalsIncludeFormat(@Nonnull TString other);

    /**
     * Checks if this string is equal to the other.
     * Ignores case, color and format.
     *
     * @param other Other string
     * @return {@code true} if the contents are the same
     */
    boolean equalsIgnoreCase(@Nonnull TString other);

    /**
     * Checks if this string is equal to the other.
     * Ignores case, but include color and format.
     *
     * @param other Other string
     * @return {@code true} if the contents and formatting are the same
     */
    boolean equalsIgnoreCaseIncludeFormat(@Nonnull TString other);

    /**
     * Checks if this string starts with the other.
     *
     * @param other Other string
     * @return {@code true} if this string starts with the other
     */
    boolean startsWith(@Nonnull TString other);

    /**
     * Checks if this string starts with the other.
     * Ignores case.
     *
     * @param other Other string
     * @return {@code true} if this string starts with the other
     */
    boolean startsWithIgnoreCase(@Nonnull TString other);

    /**
     * Checks if this string contains the other.
     *
     * @param other Other string
     * @return {@code true} if this string contains the other
     */
    boolean contains(@Nonnull TString other);

    /**
     * Checks if this string contains the other.
     * Ignores case.
     *
     * @param other Other string
     * @return {@code true} if this string contains the other
     */
    boolean containsIgnoreCase(@Nonnull TString other);

    //
    // Added features
    //

    /**
     * Appends other string to this and returns the resulting string.
     * Ignores the formatting of the inputted string.
     *
     * @param other Other string
     * @return Resulting string
     */
    @Nonnull
    TString append(@Nonnull TString other);

    /**
     * Appends list of strings to this and returns the resulting string.
     * Ignores the formatting of the inputted strings.
     *
     * <p>
     * Due to the advanced constructors provided by {@link TList}'s subtypes,
     * this can be used with incredible versatility. For example:
     * <br>
     * {@code TString starting = new TString("Vortex");} <br>
     * {@code TString result = starting.append(new BetterArrayList(new Text(" "), new Text("is the best engine!")));}
     * <br>
     * You can also use:
     * {@code TString result = starting.append(TString.of(" ", "is", " ", "the best engine!")}
     * </p>
     *
     * @param strings List of strings
     * @return Resulting string
     */
    @Nonnull
    TString append(@Nonnull TList<TString> strings);

    //
    // Util
    //

    /**
     * Gets the length of this string.
     *
     * @return Length
     */
    int length();

    /**
     * Checks if this string is empty.
     *
     * @return {@code true} if string is empty
     */
    boolean isEmpty();

    /**
     * Checks if this string is blank.
     *
     * @return {@code true} if this string is empty of only contains empty characters
     */
    boolean isBlank();

    /**
     * Checks if this string matches given regex.
     *
     * @param regex Regex
     * @return {@code true} if this string matches given regex
     * @throws PatternSyntaxException When regex is invalid
     */
    boolean matches(@Nonnull TString regex) throws PatternSyntaxException;

    /**
     * Delegates to {@link String#substring(int)}.
     *
     * @param beginIndex Beginning index
     * @return Resulting string
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    @Nonnull
    TString substring(@Nonnegative int beginIndex) throws IndexOutOfBoundsException;

    /**
     * Delegates to {@link String#substring(int, int)}.
     *
     * @param beginIndex Beginning index
     * @param endIndex   End index
     * @return Resulting string
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    @Nonnull
    TString substring(@Nonnegative int beginIndex, @Nonnegative int endIndex) throws IndexOutOfBoundsException;

    /**
     * Delegates to {@link String#concat(String)}.
     *
     * @param string String that is concatenated
     * @return Resulting string
     */
    @Nonnull
    TString concat(@Nonnull String string);

    /**
     * Replaces all occurrences of old character to new character.
     * Delegates to {@link String#replace(char, char)}.
     *
     * @param oldChar Old character
     * @param newChar New character
     * @return Resulting string
     */
    @Nonnull
    TString replace(char oldChar, char newChar);

    /**
     * Replaces target with replacement.
     * Delegates to {@link String#replace(CharSequence, CharSequence)}.
     *
     * @param target      Target to replace
     * @param replacement String to replace to
     * @return Resulting string
     */
    @Nonnull
    TString replace(@Nonnull CharSequence target, @Nonnull CharSequence replacement);

    /**
     * Replaces the first occurrence of given regex to replacement string.
     *
     * @param regex       Regex to filter
     * @param replacement String to replace to
     * @return Resulting string
     * @throws PatternSyntaxException When regex is invalid
     */
    @Nonnull
    TString replaceFirst(@Nonnull TString regex, @Nonnull TString replacement) throws PatternSyntaxException;

    /**
     * Replaces all occurrences of given regex to replacement string.
     *
     * @param regex       Regex to filter
     * @param replacement String to replace to
     * @return Resulting string
     * @throws PatternSyntaxException When regex is invalid
     */
    @Nonnull
    TString replaceAll(@Nonnull TString regex, @Nonnull TString replacement) throws PatternSyntaxException;

    /**
     * Gets the character at given index.
     *
     * @param index Index
     * @return {@link Character}
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    char charAt(@Nonnegative int index) throws IndexOutOfBoundsException;

    /**
     * Gets the byte array of this string.
     *
     * @return Byte array
     */
    @Nonnull
    byte[] getBytes();


    /**
     * Splits this string.
     *
     * @param regex Regex to split with
     * @return List of split strings
     * @throws PatternSyntaxException When the regex is invalid
     */
    @Nonnull
    TList<TString> split(@Nonnull TString regex) throws PatternSyntaxException;

    /**
     * Converts this string to lower case.
     *
     * @return Resulting string
     */
    @Nonnull
    TString toLowerCase();

    /**
     * Converts this string to upper case.
     *
     * @return Resulting string
     */
    @Nonnull
    TString toUpperCase();

    /**
     * Trims this string. (Removes excess spaces)
     *
     * @return Resulting string
     */
    @Nonnull
    TString trim();

    /**
     * Delegates to {@link String#strip()}.
     *
     * @return Stripped string
     */
    @Nonnull
    TString strip();

    /**
     * Delegates to {@link String#stripLeading()}.
     *
     * @return Stripped string
     */
    @Nonnull
    TString stripLeading();

    /**
     * Delegates to {@link String#stripTrailing()}.
     *
     * @return Stripped string
     */
    @Nonnull
    TString stripTrailing();

    /**
     * Returns a stream of lines. Delegates to {@link String#lines()}.
     *
     * @return Stream of lines
     */
    @Nonnull
    Stream<TString> lines();

    /**
     * Adjusts the indentation of this string. Delegates to {@link String#indent(int)}.
     *
     * @param delta Delta to apply
     * @return Resulting string
     */
    @Nonnull
    TString indent(int delta);

    /**
     * Delegates to {@link String#stripIndent()}.
     *
     * @return Stripped string
     */
    @Nonnull
    TString stripIndent();

    /**
     * Delegates to {@link String#translateEscapes()}.
     *
     * @return Resulting string
     * @throws IllegalArgumentException When an escape sequence is malformed
     */
    @Nonnull
    TString translateEscapes() throws IllegalArgumentException;

    /**
     * Applies a function to this string.
     *
     * @param function Function to apply
     * @param <R>      Type of result
     * @return Resulting string
     */
    @Nonnull
    <R> R transform(@Nonnull Function<? super TString, ? extends R> function);

    /**
     * Delegates to {@link String#chars()}.
     *
     * @return {@link IntStream}
     */
    @Nonnull
    IntStream chars();

    /**
     * Delegates to {@link String#toCharArray()}.
     *
     * @return Array of {@link Character}s
     */
    @Nonnull
    char[] toCharArray();
}
