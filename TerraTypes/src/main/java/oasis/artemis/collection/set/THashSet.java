package oasis.artemis.collection.set;

import oasis.artemis.collection.list.TArray;
import oasis.artemis.collection.list.TList;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <h2>THashSet</h2>
 * <p>A specialized version of {@link HashSet} used in Terra modules.</p>
 */
public class THashSet<E> extends HashSet<E> implements TSet<E> {
    /**
     * Creates a new empty THashSet.
     */
    public THashSet() {}

    /**
     * Creates a new THashSet containing given entries.
     *
     * @param entries Entries
     */
    @SafeVarargs
    public THashSet(@Nonnull E... entries) {
        this(Set.of(entries));
    }

    /**
     * Creates a new THashSet with given capacity.
     *
     * @param initialCapacity Initial capacity
     */
    public THashSet(@Nonnegative int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new THashSet with given capacity and load factor.
     *
     * @param initialCapacity Initial capacity
     * @param loadFactor      Load factor
     */
    public THashSet(@Nonnegative int initialCapacity, @Positive float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Creates a new THashSet from a collection.
     * Duplicate data will be lost.
     *
     * @param collection Collection
     */
    public THashSet(Collection<? extends E> collection) {
        super(collection);
    }

    @Nonnull
    @Override
    public TSet<E> filter(@Nonnull Predicate<E> filter) {
        return new THashSet<>(stream().filter(filter).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    public <F extends E> TSet<F> filter(@Nonnull Class<F> subtype) {
        return new THashSet<>(filter(subtype::isInstance).map(subtype::cast));
    }

    @Nonnull
    @Override
    public <F extends E> TSet<F> map(@Nonnull Function<? super E, ? extends F> mapper) {
        return new THashSet<>(stream().map(mapper).collect(Collectors.toList()));
    }

    @Override
    public TList<E> list() {
        return new TArray<>(this);
    }
}
