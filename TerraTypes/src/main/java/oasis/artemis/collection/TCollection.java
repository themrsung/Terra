package oasis.artemis.collection;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h2>TCollection</h2>
 * <p>
 * A specialized version of {@link Collection} used in Terra modules.
 * TerraCollections add delegate methods for JavaScript-like behavior.
 * </p>
 */
public interface TCollection<E> extends Collection<E> {
    /**
     * Filters this collection by given filter.
     *
     * @param filter Filter to apply
     * @return Filtered collection
     */
    @Nonnull
    TCollection<E> filter(@Nonnull Predicate<E> filter);

    /**
     * Filters this collection by given subclass, then returns a cast collection
     *
     * @param subtype Class to filter by and cast to
     * @param <F>     Type of class to use as filter
     * @return Filtered and cast collection
     */
    @Nonnull
    <F extends E> TCollection<F> filter(@Nonnull Class<F> subtype);

    /**
     * Maps this collection and returns the resulting stream.
     *
     * @param mapper Mapper to use
     * @param <F>    Subtype to map to
     * @return Mapped collection
     */
    @Nonnull
    <F extends E> TCollection<F> map(@Nonnull Function<? super E, ? extends F> mapper);
}
