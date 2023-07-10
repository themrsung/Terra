package oasis.artemis.collection.list;

import oasis.artemis.collection.TCollection;
import oasis.artemis.collection.set.TSet;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h2>TList</h2>
 * <p>A specialized version of {@link List} used in Terra modules.</p>
 */
public interface TList<E> extends TCollection<E>, List<E> {
    /**
     * Converts this list to a set.
     * Duplicate data is lost.
     *
     * @return Converted set
     */
    @Nonnull
    TSet<E> set();

    /**
     * Gets the unique number of elements in this list.
     * Duplicates will be counted as one.
     *
     * @return Unique size
     */
    @Nonnegative
    int uniqueSize();

    @Nonnull
    @Override
    TList<E> filter(@Nonnull Predicate<E> filter);

    @Nonnull
    @Override
    <F extends E> TList<F> filter(@Nonnull Class<F> subtype);

    @Nonnull
    @Override
    <F extends E> TList<F> map(@Nonnull Function<? super E, ? extends F> mapper);
}
