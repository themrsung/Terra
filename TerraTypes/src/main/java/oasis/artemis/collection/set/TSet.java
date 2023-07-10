package oasis.artemis.collection.set;

import oasis.artemis.collection.TCollection;
import oasis.artemis.collection.list.TList;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <h2>TSet</h2>
 * <p>A specialized version of {@link Set} used in Terra modules.</p>
 */
public interface TSet<E> extends TCollection<E>, Set<E> {
    /**
     * Converts this set to a list.
     *
     * @return Converted list
     */
    @Nonnegative
    TList<E> list();

    @Nonnull
    @Override
    TSet<E> filter(@Nonnull Predicate<E> filter);

    @Nonnull
    @Override
    <F extends E> TSet<F> filter(@Nonnull Class<F> subtype);

    @Nonnull
    @Override
    <F extends E> TSet<F> map(@Nonnull Function<? super E, ? extends F> mapper);
}
